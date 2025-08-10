package com.ra.validator;

import com.ra.model.Bus;
import com.ra.service.BusService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PlateUniqueValidator implements ConstraintValidator<PlateUnique, Bus> {
    @Autowired
    private BusService busService;

    @Override
    public void initialize(PlateUnique constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Bus bus, ConstraintValidatorContext constraintValidatorContext) {
        if (bus == null) return true;

        return busService.isLicensePlateUnique(
                bus.getId() == null ? 0 : bus.getId(),
                bus.getLicensePlate()
        );
    }
}
