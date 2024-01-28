package carselling.selling.repository.statistics;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import carselling.selling.entity.statistics.BrandSoldPerYear;
import carselling.selling.entity.statistics.CarSoldPerMonthForYear;
import carselling.selling.entity.statistics.CarSoldPerYear;
import carselling.selling.entity.Car;

public interface CarSoldRepository extends CrudRepository<Car, String>{

    @Query(nativeQuery = true,value = "SELECT * FROM v_most_sold_brand_per_year order by sum desc")
    public List<BrandSoldPerYear> getMostSoldBrand();


    @Query(nativeQuery = true,value = "SELECT * FROM v_most_sold_brand_per_year WHERE year = :year order by sum desc")
    public List<BrandSoldPerYear> getMostSoldBrandForYear(int year);

    @Query(nativeQuery = true,value = "SELECT * FROM v_most_sold_car_per_year order by sum desc")
    public List<CarSoldPerYear> getMostSoldCar();

    @Query(nativeQuery = true,value = "SELECT * FROM v_sales_count_by_month WHERE year = :year AND model_id LIKE :id order by sum desc")
    public List<CarSoldPerMonthForYear> getSaleStatsOfModelForYear(@Param(value = "year") int year, @Param(value = "id") String id);

    @Query(nativeQuery = true,value = "SELECT * FROM v_sales_count_by_month WHERE year = :year order by sum desc")
    public List<CarSoldPerMonthForYear> getSaleStatsForYear(@Param(value = "year") int year);
}

