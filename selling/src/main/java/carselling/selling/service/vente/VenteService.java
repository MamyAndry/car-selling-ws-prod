package carselling.selling.service.vente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import carselling.selling.entity.Fund;
import carselling.selling.entity.Profit;
import carselling.selling.entity.Vente;
import carselling.selling.repository.CommissionRepository;
import carselling.selling.repository.FundRepository;
import carselling.selling.repository.ProfitRepository;
import carselling.selling.repository.VenteRepository;

@Service
public class VenteService {
    @Autowired
    CommissionRepository commissionRepository;
    @Autowired
    ProfitRepository profitRepository;
    @Autowired
    FundRepository fundRepository;
    @Autowired
    VenteRepository venteRepository;


    public ResponseEntity<?> sellCar(Vente vente) throws Exception{
        Double percentage = commissionRepository.getCommissionPercentage(vente.getPricePayed());
        Double commission = (vente.getPricePayed() * percentage) / 100;
        Double risingObtained = vente.getPricePayed() - commission;

        Profit profit = new Profit();
        profit.setDateAdd(vente.getDateValidation());
        profit.setUser(vente.getSeller());
        profit.setRising(risingObtained);

        Fund fund = new Fund();
        fund.setDateAdd(vente.getDateValidation());
        fund.setVente(vente);
        fund.setRising(commission);

        profitRepository.save(profit);
        fundRepository.save(fund);

        vente.setStatus(10);
        return ResponseEntity.ok(venteRepository.save(vente));
    }
}
