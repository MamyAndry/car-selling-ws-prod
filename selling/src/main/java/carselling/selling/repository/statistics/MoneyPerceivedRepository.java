package carselling.selling.repository.statistics;

import carselling.selling.entity.Sale;
import carselling.selling.entity.statistics.FundPerMonthPerYear;
import carselling.selling.entity.statistics.FundPerYear;
import carselling.selling.entity.statistics.ProfitPerMonthPerYear;
import carselling.selling.entity.statistics.ProfitPerYear;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MoneyPerceivedRepository extends CrudRepository<Sale, String> {
    

    @Query(nativeQuery = true,value = "SELECT * FROM v_profit_user_per_month WHERE id_user_result LIKE :id")
    public List<ProfitPerMonthPerYear> getProfitPerMonthPerYear(@Param(value = "id") String id);

    @Query(nativeQuery = true,value = "SELECT * FROM v_profit_variation_per_year WHERE id = :id")
    public List<ProfitPerYear> getProfitPerYear(@Param(value = "id") String id);

    @Query(nativeQuery = true,value = "SELECT * FROM v_profit_user_per_month WHERE year = :year AND id_user_result LIKE :id")
    public List<ProfitPerYear> getProfitOfYear(@Param(value = "id") String id,@Param(value = "year") int year);

    @Query(nativeQuery = true,value = "SELECT * FROM v_fund_user_per_month")
    public List<FundPerMonthPerYear> getFundVariationPerMonthPerYear();

    @Query(nativeQuery = true,value = "SELECT * FROM v_fund_user_per_month WHERE year = :year")
    public List<FundPerMonthPerYear> getFundVariationForYear(@Param(value = "year") int year);

    @Query(nativeQuery = true,value = "SELECT * FROM v_fund_variation_per_year")
    public List<FundPerYear> getFundVariationPerYear();
}
