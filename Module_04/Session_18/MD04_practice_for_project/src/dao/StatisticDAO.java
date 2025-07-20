package dao;

import entity.Product;
import entity.StatisticEmployee;
import entity.StatisticProduct;
import entity.StatisticRevenueCost;

import java.time.LocalDate;
import java.util.List;

public interface StatisticDAO {
    List<StatisticRevenueCost> statisticRevenueCostByDate(int billType);

    List<StatisticRevenueCost> statisticRevenueCostByMonth(int billType);

    List<StatisticRevenueCost> statisticRevenueCostByYear(int billType);

    List<StatisticRevenueCost> statisticRevenueCostInRange(int billType, LocalDate startDate, LocalDate endDate);

    List<StatisticEmployee> statisticEmployeeByStatus();

    StatisticProduct statisticProductMaxTradeInRange(int billType, LocalDate startDate, LocalDate endDate);

    StatisticProduct statisticProductMinTradeInRange(int billType, LocalDate startDate, LocalDate endDate);
}
