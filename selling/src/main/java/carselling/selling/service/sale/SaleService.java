package carselling.selling.service.sale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carselling.selling.entity.Fund;
import carselling.selling.entity.Profit;
import carselling.selling.entity.Sale;
import carselling.selling.repository.CommissionRepository;
import carselling.selling.repository.FundRepository;
import carselling.selling.repository.ProfitRepository;
import carselling.selling.repository.SaleRepository;

@Service
public class SaleService {
    @Autowired
    CommissionRepository commissionRepository;
    @Autowired
    ProfitRepository profitRepository;
    @Autowired
    FundRepository fundRepository;
    @Autowired
    SaleRepository saleRepository;


    public ResponseEntity<?> sellCar(Sale sale) throws Exception{
        Double percentage = commissionRepository.getCommissionPercentage(sale.getPricePayed());
        Double commission = (sale.getPricePayed() * percentage) / 100;
        Double risingObtained = sale.getPricePayed() - commission;

        Profit profit = new Profit();
        profit.setDateAddition(sale.getDateValidation());
        profit.setUser(sale.getSeller());
        profit.setRising(risingObtained);

        Fund fund = new Fund();
        fund.setDateAddition(sale.getDateValidation());
        fund.setSale(sale);
        fund.setRising(commission);

        profitRepository.save(profit);
        fundRepository.save(fund);

        sale.setStatus(10);
        return ResponseEntity.ok(saleRepository.save(sale));
    }
}
