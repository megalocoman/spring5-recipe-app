package guru.springframework.converters;

import guru.springframework.command.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand source) {

        if(source != null){
            final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
            unitOfMeasure.setId(source.getId());
            unitOfMeasure.setUom(source.getUom());
            return unitOfMeasure;
        }
        return null;
    }
}
