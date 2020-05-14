package ua.nure.vasilchenko.SummaryTask4.db;

import org.apache.log4j.Logger;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Card;
import ua.nure.vasilchenko.SummaryTask4.db.entity.Payment;
import ua.nure.vasilchenko.SummaryTask4.db.entity.User;
import ua.nure.vasilchenko.SummaryTask4.exception.DBException;
import ua.nure.vasilchenko.SummaryTask4.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * DB manager. Works with MySQL. Only the required DAO methods are
 * defined!
 *
 * @author S.Vasilchenko
 */
public final class DBManager {

    /**
     * logging.
     */
    private static final Logger LOG = Logger.getLogger(DBManager.class);

    /**
     * data source from context.xml.
     */
    private DataSource ds;

    // //////////////////////////////////////////////////////////
    // singleton
    // //////////////////////////////////////////////////////////

    private static DBManager instance;

    public static synchronized DBManager getInstance() throws DBException {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

//    // Uncomment this String to run test.
//    String connectionUrl;
//
//    /**
//     * Uncomment this method to run test.
//     * And comment similar bottom method.
//     * <p>
//     * This constructor is just for a example run test
//     * to obtain a DB connection. It does not use a pool
//     * connections and not used in this project.
//     * <p>
//     * Only for tests!
//     */
//    private DBManager() {
//        Properties properties = new Properties();
//        try {
//            properties.load(new FileInputStream("test/ua/nure/vasilchenko/SummaryTask4/app.properties"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        synchronized (this) {
//            connectionUrl = properties.getProperty("connection.url");
//        }
//    }
//
//    /**
//     * Uncomment this method to run test.
//     * And comment similar bottom method.
//     * <p>
//     * Returns a DB connection. This method is just for a example run test
//     * to obtain a DB connection. It does not use a pool
//     * connections and not used in this project.
//     * <p>
//     * Only for test!
//     *
//     * @return connection to DB payment.
//     * @throws SQLException
//     * @throws DBException
//     */
//    public Connection getConnection() throws SQLException, DBException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        Connection connection = DriverManager
//                .getConnection(connectionUrl);
//        connection
//                .setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
//        connection.setAutoCommit(false);
//        return connection;
//    }

    /**
     * Constructor for realization single ton. Read data from context.xml
     *
     * @throws DBException
     */
    private DBManager() throws DBException {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            // PAYMENT - the name of data source
            ds = (DataSource) envContext.lookup("jdbc/PAYMENT");
            LOG.trace("Data source ==> " + ds);
        } catch (NamingException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
        }
    }

    /**
     * Returns a DB connection from the Pool Connections. Before using this
     * method you must configure the Date Source and the Connections Pool in
     * your WEB_APP_ROOT/META-INF/context.xml file.
     *
     * @return DB connection.
     */
    public Connection getConnection() throws DBException, SQLException {
        Connection con;
        try {
            con = ds.getConnection();
        } catch (SQLException ex) {
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
        }
        return con;
    }


    // //////////////////////////////////////////////////////////
    // SQL queries
    // //////////////////////////////////////////////////////////


    private static final String SQL_FIND_USER_BY_LOGIN = "SELECT * FROM payment.users WHERE login=?";
    private static final String SQL_FIND_USER_BY_ID = "SELECT * FROM payment.users WHERE id=?";
    private static final String SQL_FIND_CARD_BY_ID = "SELECT * FROM payment.cards WHERE id=?";
    private static final String SQL_FIND_CARD_BY_NUMBER = "SELECT * FROM payment.cards WHERE number=?";
    private static final String SQL_FIND_PAYMENT_BY_ID = "SELECT * FROM payment.payments WHERE id=?";
    private static final String SQL_DELETE_PAYMENT = "DELETE FROM payment.payments WHERE id=?";
    private static final String SQL_FIND_ALL_CLIENTS = "SELECT * FROM payment.users WHERE role_id=1";
    private static final String SQL_FIND_USER_CARDS = "SELECT * FROM payment.cards WHERE user_id=?";
    private static final String SQL_FIND_CARDS_REQUEST = "SELECT * FROM payment.cards WHERE request_id=1";
    private static final String SQL_UPDATE_USER = "UPDATE payment.users SET  first_name=?, last_name=?, activity_id=?"
            + "	WHERE id=?";
    private static final String SQL_UPDATE_PAYMENT = "UPDATE payment.payments SET  date=(SELECT CURRENT_TIMESTAMP), " +
            "status_id=?, balance=? WHERE id=?";
    private static final String SQL_UPDATE_CARD_ACTIVITY_REQUEST = "UPDATE payment.cards SET activity_id=?," +
            " request_id=?, money=? WHERE id=?";
    private static final String SQL_INSERT_USER = "INSERT payment.users VALUES (DEFAULT, ?, ?, ?, ?, DEFAULT, DEFAULT)";
    private static final String SQL_INSERT_CARD = "INSERT payment.cards VALUES (DEFAULT, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_COUNT_REQUEST = "SELECT COUNT(*) AS COUNTREQ FROM payment.cards" +
            " WHERE request_id=1 AND user_id=?";
    private static final String SQL_COUNT_REQUEST_ADMIN = "SELECT COUNT(*) AS COUNTREQ FROM payment.cards" +
            " WHERE request_id=1";
    private static final String SQL_INSERT_PAYMENT = "INSERT payment.payments VALUES " +
            "(DEFAULT, ?, ?,?, ?,(SELECT CURRENT_TIMESTAMP),?)";
    private static final String SQL_USER_PAYMENTS = "select * from payment.payments" +
            " where card_id in (SELECT id from payment.cards where user_id=?)" +
            " order by date";
    private static final String SQL_USER_INCOME = "select * from payment.payments" +
            " where card_destination_id in (SELECT id from payment.cards where user_id=?) and status_id=1" +
            " order by date";
    private static final String SQL_CREATE_SELECTION_BY_DATE = "select * from payment.payments where DATE(date) = ?";



    // //////////////////////////////////////////////////////////
    // Entity access methods
    // //////////////////////////////////////////////////////////

    /**
     * Method insert in DB in table 'users'
     * new user from entity.
     *
     * @param user - from command register.
     */
    public void insertUser(User user) throws DBException, SQLException {
        Connection con = getConnection();
        try {
            if (findUserByLogin(user.getLogin()) != null) {
                return;
            }
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_USER);
            LOG.trace("Preparation statement: " + ps);
            ps.setString(1, user.getLogin());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getLastName());
            ps.executeUpdate();
            con.commit();
            LOG.trace("Statement commit: " + ps);
        } catch (SQLException ex) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_INSERT_USER, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_USER, ex);
        } finally {
            close(con);
        }
    }

    public List<Payment> getPaymentsByDate(String date) throws DBException {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SQL_CREATE_SELECTION_BY_DATE);
            LOG.trace("Get prepared: " + stmt);
            stmt.setString(1, date);
            rs = stmt.executeQuery();
            while (rs.next()) {
                payments.add(extractPayment(rs));
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_PAYMENTS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_PAYMENTS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return payments;
    }

    /**
     * Method insert in DB in table 'cards'
     * new card from entity.
     *
     * @param card - add from command register and create_card.
     */
    public void insertCard(Card card) throws DBException, SQLException {
        Connection con = getConnection();
        try {
            if (findCardByNumber(card.getNumber()) != null) {
                return;
            }
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_CARD);
            LOG.trace("Preparation statement: " + ps);
            ps.setLong(1, card.getUserId());
            ps.setString(2, card.getName());
            ps.setLong(3, card.getNumber());
            ps.setInt(4, card.getMoney());
            ps.setInt(5, card.getActivityId());
            ps.setInt(6, card.getRequestId());
            ps.executeUpdate();
            con.commit();
            LOG.trace("Statement commit: " + ps);
        } catch (SQLException ex) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_INSERT_CARD, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_CARD, ex);
        } finally {
            close(con);
        }
    }


    /**
     * Method insert in DB in table 'payments'
     * new payment from entity.
     *
     * @param payment get from command:
     *                sendPayment -> checkPayment -> confirmPayment.
     */
    public void insertPayment(Payment payment) throws DBException, SQLException {
        Connection con = getConnection();
        try {
            PreparedStatement ps = con.prepareStatement(SQL_INSERT_PAYMENT);
            LOG.trace("Preparation statement: " + ps);
            if (payment.getCardId() != 0) {
                ps.setLong(1, payment.getCardId());
            } else {
                ps.setString(1, null);
            }
            ps.setLong(2, payment.getCardDestinationId());
            ps.setInt(3, payment.getMoney());
            ps.setInt(4, payment.getBalance());
            ps.setInt(5, payment.getStatusId());
            ps.executeUpdate();
            con.commit();
            LOG.trace("Statement commit: " + ps);
        } catch (SQLException ex) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_INSERT_PAYMENT, ex);
            throw new DBException(Messages.ERR_CANNOT_INSERT_PAYMENT, ex);
        } finally {
            close(con);
        }
    }

    /**
     * Find all items users in DB, which role = 'client'.
     *
     * @return list user, where stored all clients
     * this program.
     */
    public List<User> findAllUsersClient() throws DBException {
        List<User> usersList = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQL_FIND_ALL_CLIENTS);
            LOG.trace("Get result set for listUsers: " + rs);
            while (rs.next()) {
                usersList.add(extractUser(rs));
            }
            con.commit();
            LOG.trace("Commit connection: " + rs);
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_USERS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_USERS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return usersList;
    }

    /**
     * Returns a user with the given identifier.
     *
     * @param id User identifier.
     * @return User entity.
     * @throws DBException
     */
    public User findUser(long id) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_ID);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            LOG.trace("Get result set for listUsers: " + rs);
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
            LOG.trace("Commit connection: " + rs);
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Return a card with the given identifier.
     *
     * @param id Card identifier.
     * @return card entity.
     * @throws DBException
     */
    public Card findCard(long id) throws DBException {
        Card card = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_CARD_BY_ID);
            LOG.trace("Get prepared: " + pstmt);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                card = extractCard(rs);
            }
            con.commit();
            LOG.trace("Commin connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARD_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return card;
    }

    /**
     * Return a card with the given number.
     *
     * @param number Card field.
     * @return card entity.
     * @throws DBException
     */
    public Card findCardByNumber(long number) throws DBException {
        Card card = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_CARD_BY_NUMBER);
            LOG.trace("Get prepared: " + pstmt);
            pstmt.setLong(1, number);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                card = extractCard(rs);
            }
            con.commit();
            LOG.trace("Commin connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_CARD_BY_NUMBER, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_CARD_BY_NUMBER, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return card;
    }

    /**
     * Return a payment with the given id.
     *
     * @param id Payment identifier.
     * @return payment entity
     * @throws DBException
     */
    public Payment findPayment(long id) throws DBException {
        Payment payment = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_PAYMENT_BY_ID);
            LOG.trace("Get prepared: " + pstmt);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                payment = extractPayment(rs);
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_PAYMENT_BY_ID, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_PAYMENT_BY_ID, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return payment;
    }

    /**
     * Update given payment in DB by now parameters.
     *
     * @param payment - from command defer, updating in DB.'
     * @throws DBException
     */
    public void updatePayment(Payment payment) throws DBException {
        PreparedStatement pstmt = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_UPDATE_PAYMENT);
            LOG.trace("Get prepared: " + pstmt);
            pstmt.setLong(1, payment.getStatusId());
            pstmt.setLong(2, payment.getBalance());
            pstmt.setLong(3, payment.getId());
            pstmt.executeUpdate();
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_UPDATE_PAYMENT, ex);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_PAYMENT, ex);
        } finally {
            close(con);
            close(pstmt);
        }
    }

    /**
     * Get user by method findUser, with arguments
     * gets from method findCard, which get id Card
     * by user id, which the gets from field in entity.
     *
     * @param id Card identifier
     * @return User entity, got from other methods.
     * @throws DBException
     */
    public User findUserByCardId(long id) throws DBException {
        LOG.trace("Go to many methods");
        return findUser(findCard(id).getUserId());
    }

    /**
     * Find user`s cards, by identifier user.
     *
     * @param user entity.
     * @return list cards owned by user entity.
     * @throws DBException
     */
    public List<Card> getUserCards(User user) throws DBException {
        List<Card> cards = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SQL_FIND_USER_CARDS);
            LOG.trace("Get prepared: " + stmt);
            stmt.setLong(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                cards.add(extractCard(rs));
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_CARDS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_CARDS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return cards;
    }

    /**
     * Return all payments from DB get by request,
     * what get cards by user id and payments get by card id.
     *
     * @param user entity.
     * @return list payments by user id.
     * @throws DBException
     */
    public List<Payment> getUserPayments(User user) throws DBException {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SQL_USER_PAYMENTS);
            LOG.trace("Get prepared: " + stmt);
            stmt.setLong(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                payments.add(extractPayment(rs));
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_PAYMENTS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_PAYMENTS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return payments;
    }

    /**
     * Return all payments which the income to user from DB get by request,
     * what get cards by user id and payments get by card id.
     *
     * @param user entity.
     * @return list payments income by user id.
     * @throws DBException
     */
    public List<Payment> getUserIncome(User user) throws DBException {
        List<Payment> payments = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SQL_USER_INCOME);
            LOG.trace("Get prepared: " + stmt);
            stmt.setLong(1, user.getId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                payments.add(extractPayment(rs));
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_PAYMENTS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_PAYMENTS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return payments;
    }

    /**
     * Get cards from DB, which the requests to unblock cards.
     *
     * @return list cards, where need show
     * in administrator page for unblock.
     * @throws DBException
     */
    public List<Card> getCardsWithRequest() throws DBException {
        List<Card> cards = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            stmt = con.prepareStatement(SQL_FIND_CARDS_REQUEST);
            LOG.trace("Get prepared: " + stmt);
            rs = stmt.executeQuery();
            while (rs.next()) {
                cards.add(extractCard(rs));
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException | DBException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_FIND_ALL_CARDS, ex);
            throw new DBException(Messages.ERR_CANNOT_FIND_ALL_CARDS, ex);
        } finally {
            close(con, stmt, rs);
        }
        return cards;
    }

    /**
     * Returns a user with the given login.
     *
     * @param login User login.
     * @return User entity.
     * @throws DBException
     */
    public User findUserByLogin(String login) throws DBException {
        User user = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_FIND_USER_BY_LOGIN);
            LOG.trace("Get prepared: " + pstmt);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = extractUser(rs);
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
            throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return user;
    }

    /**
     * Get from DB count requests for certain users.
     *
     * @param user entity.
     * @return number user`s requests.
     * @throws DBException
     */
    public int countRequest(User user) throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_COUNT_REQUEST);
            LOG.trace("Get prepared: " + pstmt);
            pstmt.setLong(1, user.getId());
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNTREQ");
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_COUNT_REQUESTS, ex);
            throw new DBException(Messages.ERR_CANNOT_COUNT_REQUESTS, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return count;
    }

    /**
     * Get from DB count requests for all users.
     *
     * @return number all users`s requests.
     * @throws DBException
     */
    public int countRequestAdmin() throws DBException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection con = null;
        int count = 0;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(SQL_COUNT_REQUEST_ADMIN);
            LOG.trace("Get prepared: " + pstmt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                count = rs.getInt("COUNTREQ");
            }
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException ex) {
            rollback(con);
            LOG.error(Messages.ERR_CANNOT_COUNT_REQUESTS, ex);
            throw new DBException(Messages.ERR_CANNOT_COUNT_REQUESTS, ex);
        } finally {
            close(con, pstmt, rs);
        }
        return count;
    }

    /**
     * Update user.
     *
     * @param user user to update.
     * @throws DBException
     */
    public void updateUser(User user) throws DBException, SQLException {
        PreparedStatement statement = null;
        Connection con = getConnection();
        try {
            statement = con.prepareStatement(SQL_UPDATE_USER);
            LOG.trace("Get prepared: " + statement);
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setInt(3, user.getActivityId());
            statement.setLong(4, user.getId());
            statement.executeUpdate();
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_UPDATE_USER, e);
            e.printStackTrace();
        } finally {
            close(con);
            close(statement);
        }
    }

    /**
     * Delete payment by id.
     *
     * @param id payment identifier.
     * @throws DBException
     * @throws SQLException
     */
    public void deletePayment(long id) throws DBException, SQLException {
        PreparedStatement statement = null;
        Connection con = getConnection();
        try {
            statement = con.prepareStatement(SQL_DELETE_PAYMENT);
            LOG.trace("Get prepared: " + statement);
            statement.setLong(1, id);
            statement.executeUpdate();
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException e) {
            con.rollback();
            LOG.error(Messages.ERR_CANNOT_DELETE_PAYMENT, e);
            throw new DBException(Messages.ERR_CANNOT_DELETE_PAYMENT, e);
        } finally {
            close(con);
            close(statement);
        }
    }


    /**
     * Update card in DB by entity.
     *
     * @param card entity.
     * @throws DBException
     * @throws SQLException
     */
    public void updateCard(Card card) throws DBException, SQLException {
        PreparedStatement statement = null;
        Connection con = getConnection();
        try {
            statement = con.prepareStatement(SQL_UPDATE_CARD_ACTIVITY_REQUEST);
            LOG.trace("Get prepared: " + statement);
            statement.setInt(1, card.getActivityId());
            statement.setInt(2, card.getRequestId());
            statement.setInt(3, card.getMoney());
            statement.setLong(4, card.getId());
            statement.executeUpdate();
            con.commit();
            LOG.trace("Commit connection " + con);
        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
            LOG.error(Messages.ERR_CANNOT_UPDATE_CARD, e);
            throw new DBException(Messages.ERR_CANNOT_UPDATE_CARD, e);
        } finally {
            close(con);
            close(statement);
        }
    }


    // //////////////////////////////////////////////////////////
    // DB util methods
    // //////////////////////////////////////////////////////////

    /**
     * Closes a connection.
     *
     * @param con Connection to be closed.
     */
    private void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
            }
        }
    }

    /**
     * Closes a statement object.
     */
    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
            }
        }
    }

    /**
     * Closes a result set object.
     */
    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
            }
        }
    }

    /**
     * Closes resources.
     */
    private void close(Connection con, Statement stmt, ResultSet rs) {
        close(rs);
        close(stmt);
        close(con);
    }

    /**
     * Rollbacks a connection.
     *
     * @param con Connection to be rollbacked.
     */
    private void rollback(Connection con) {
        if (con != null) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                LOG.error("Cannot rollback transaction", ex);
            }
        }
    }

    // //////////////////////////////////////////////////////////
    // Other methods
    // //////////////////////////////////////////////////////////

    /**
     * Extracts a user entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return User entity
     */
    private User extractUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(Fields.ENTITY_ID));
        user.setLogin(rs.getString(Fields.USER_LOGIN));
        user.setPassword(rs.getString(Fields.USER_PASSWORD));
        user.setFirstName(rs.getString(Fields.USER_FIRST_NAME));
        user.setLastName(rs.getString(Fields.USER_LAST_NAME));
        user.setRoleId(rs.getInt(Fields.USER_ROLE_ID));
        user.setActivityId(rs.getInt(Fields.USER_ACTIVITY_ID));
        return user;
    }

    /**
     * Extracts a card entity from the result set.
     *
     * @param rs Result set from which a user entity will be extracted.
     * @return Card entity
     */
    private Card extractCard(ResultSet rs) throws SQLException {
        Card card = new Card();
        card.setId(rs.getLong(Fields.ENTITY_ID));
        card.setUserId(rs.getInt(Fields.CARD_USER_ID));
        card.setName(rs.getString(Fields.CARD_NAME));
        card.setNumber(rs.getLong(Fields.CARD_NUMBER));
        card.setMoney(rs.getInt(Fields.CARD_MONEY));
        card.setActivityId(rs.getInt(Fields.CARD_ACTIVITY_ID));
        card.setRequestId(rs.getInt(Fields.CARD_REQUEST_ID));
        return card;
    }

    /**
     * Extracts a payment entity from the result set.
     *
     * @param rs Result set from which a payment entity will be extracted.
     * @return Payment entity
     */
    private Payment extractPayment(ResultSet rs) throws SQLException {
        Payment payment = new Payment();
        payment.setId(rs.getLong(Fields.ENTITY_ID));
        payment.setCardId(rs.getInt(Fields.PAYMENT_CARD_ID));
        payment.setCardDestinationId(rs.getInt(Fields.PAYMENT_CARD_DESTINATION_ID));
        payment.setDate(rs.getString(Fields.PAYMENT_DATE));
        payment.setBalance(rs.getInt(Fields.PAYMENT_BALANCE));
        payment.setMoney(rs.getInt(Fields.PAYMENT_MONEY));
        payment.setStatusId(rs.getInt(Fields.PAYMENT_STATUS_ID));
        return payment;
    }
}
