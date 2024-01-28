package carselling.selling.controller.advancedResearch;

import carselling.selling.entity.Announcement;
import carselling.selling.entity.advancedResearch.CarDetails;
import carselling.selling.response.ApiResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "advancedReseach")
public class CarDetailsController {
    private EntityManager entityManager;
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse> advancedResearch(@RequestBody CarDetails carDetails){
        ApiResponse apiResponse = new ApiResponse();
        try{
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Announcement> query = builder.createQuery(Announcement.class);
            Root<Announcement> root =query.from(Announcement.class);
            List<Predicate> predicates = new ArrayList<>();

            Announcement annonce = new Announcement();

            annonce.searchByGearBox(carDetails.getIdGearBox(), predicates, builder, root);
            annonce.searchByColor(carDetails.getColor(), predicates, builder, root);
            annonce.searchByTransmission(carDetails.getIdTransmission(), predicates, builder, root);
            annonce.searchByModel(carDetails.getIdModel(), predicates, builder, root);
            annonce.searchByBrand(carDetails.getIdBrand(), predicates, builder, root);
            annonce.searchByCategory(carDetails.getIdCategory(), predicates, builder, root);
            annonce.searchByFuelType(carDetails.getIdFuelType(), predicates, builder, root);
            annonce.searchByDescription(carDetails.getDescription(), predicates, builder, root);
            annonce.searchByCarStatus(carDetails.getIdCarStatus(), predicates, builder, root);
            annonce.searchByKilometrage(carDetails.getMin_kilometrage(), carDetails.getMax_kilometrage(), predicates, builder, root);
            annonce.searchByMotor(carDetails.getIdMotor(), predicates, builder, root);
            annonce.searchByPrice(carDetails.getMin_price(), carDetails.getMax_price(), predicates, builder, root);
            annonce.searchByLocation(carDetails.getIdLocation(), predicates, builder, root);

            query.where(predicates.toArray(new Predicate[0]));

            List<Announcement> results = entityManager.createQuery(query).getResultList();

            apiResponse.addData("data", results);
            return ResponseEntity.ok(apiResponse);
        } catch (Exception e){
            apiResponse.addError("error", e.getCause().getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
        }
    }

}
