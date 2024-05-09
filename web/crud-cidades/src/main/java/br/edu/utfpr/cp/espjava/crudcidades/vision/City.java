package br.edu.utfpr.cp.espjava.crudcidades.vision;

public final class City {
    private final String name;
    private final String state;

    public City(final String name, final String state) {
        this.name = name;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

}
