package br.edu.utfpr.cp.espjava.crudcidades.city;

import br.edu.utfpr.cp.espjava.crudcidades.city.entities.CityEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public final class City {

    @NotBlank(message = "{app.city.blank}")
    @Size(min = 5, max = 60, message = "{app.city.size}")
    private final String name;

    @NotBlank(message = "{app.state.blank}")
    @Size(min = 2, max = 2, message = "{app.state.size}")
    private final String state;

    public City(final String name, final String state) {
        this.name = name;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getName() {
        return name;
    }

    public CityEntity clone() {
        var cityEntity = new CityEntity();
        
        cityEntity.setName(this.getName());
        cityEntity.setState(this.getState());

        return cityEntity;
    }

    public City clone(CityEntity city) {
        return new City(city.getName(), city.getState());
    }
}