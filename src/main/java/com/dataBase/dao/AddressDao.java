package com.dataBase.dao;

import com.model.Address;
import com.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressDao implements Dao<Address> {
    private static final Connection connection = MySqlConnection.getInstance().getConnection();
    private static final String SELECT_FROM_ADDRESS = "SELECT * FROM address";
    private static final String DELETE_FROM_ADDRESS = "DELETE FROM address WHERE address_id=?";
    private static final String INSERT_TO_ADDRESS = "INSERT INTO address(street, city, zip_code) VALUES (?,?,?)";
    private static final String INSERT_INTO_ADDRESS_WITH_EMPLOYEE_ID = "INSERT INTO address(street, city, zip_code,employee_id) VALUES (?,?,?,?)";
    private static final String SELECT_WITH_CONDITION = "SELECT * FROM address WHERE address_id=?";
    private static final String UPDATE_FROM_ADDRESS = "UPDATE address SET street = ?,city = ?, zip_code = ? WHERE address_id = ?";


    @Override
    public List<Address> getAll() {
        List<Address> address = new ArrayList<>();
        try (Statement statement = Objects.requireNonNull(connection).createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_FROM_ADDRESS);
            while (resultSet.next()) {
                Long id = resultSet.getLong("address_id");
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                int zipCode = resultSet.getInt("zip_code");
                address.add(new Address(id, street, city, zipCode));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void remove(Long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(DELETE_FROM_ADDRESS);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Address getById(Long id) {
        PreparedStatement preparedStatement = null;
        Address address = new Address();
        try {
            preparedStatement = Objects.requireNonNull(connection).prepareStatement(SELECT_WITH_CONDITION);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String street = resultSet.getString("street");
                String city = resultSet.getString("city");
                int zipCode = resultSet.getInt("zip_code");
                address.setStreet(street);
                address.setZipCode(zipCode);
                address.setCity(city);
                //return address;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public void add(Address address) {
        try {
            PreparedStatement preparedStatement = getPreparedStatement(address, INSERT_TO_ADDRESS);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addJobForEmployee(Address address, Long employeeId){
        try {
            PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(INSERT_INTO_ADDRESS_WITH_EMPLOYEE_ID);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setInt(3, address.getZipCode());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setLong(4,employeeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public Long update(Address address, Long id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FROM_ADDRESS);
            preparedStatement.setString(1, address.getStreet());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setInt(3, address.getZipCode());
            if (address.getId() != null) {
                preparedStatement.setLong(4, id);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return address.getId();
    }
    private PreparedStatement getPreparedStatement(Address address,String line) throws SQLException {
        PreparedStatement preparedStatement = Objects.requireNonNull(connection).prepareStatement(line);
        preparedStatement.setString(1, address.getStreet());
        preparedStatement.setString(2, address.getCity());
        preparedStatement.setInt(3, address.getZipCode());
        return preparedStatement;
    }


}
