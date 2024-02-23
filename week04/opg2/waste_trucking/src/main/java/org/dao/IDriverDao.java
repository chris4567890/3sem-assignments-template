package org.dao;

import org.model.Driver;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public interface IDriverDao {
    String saveDriver(String name, String surname, BigInteger salary);
    Driver getDriverById(String id);
    Driver updateDriver(Driver driver);
    void deleteDriver(String id);
    List<Driver> findAllDriversEmployedAtTheSameYear(String year);
    List<Driver> fetchAllDriversWithSalaryGreaterThan10000();
    BigInteger fetchHighestSalary();
    List<String> fetchFirstNameOfAllDrivers();
    long calculateNumberOfDrivers();
    Driver fetchDriverWithHighestSalary();


}
