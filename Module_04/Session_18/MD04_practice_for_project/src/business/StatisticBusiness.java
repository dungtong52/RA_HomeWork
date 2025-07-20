package business;

import entity.StatisticEmployee;
import entity.StatisticProduct;
import entity.StatisticRevenueCost;

import java.time.LocalDate;
import java.util.List;

public interface StatisticBusiness {
    List<StatisticRevenueCost> statisticRevenueCostByDate(String type, int billType);

    List<StatisticRevenueCost> statisticRevenueCostInRange(int billType, LocalDate startDate, LocalDate endDate);

    List<StatisticEmployee> statisticEmployeeByStatus();

    StatisticProduct statisticProductInRange(String type, int billType, LocalDate startDate, LocalDate endDate);
}
