package model;

public class Currency {
    private final String code;
    private final String symbol;
    private final String name;

    public Currency(String code, String name,String symbol) {
        this.code = code;
        this.symbol = symbol;
        this.name = name;
    }

    public String getCode() {
        return code;
    }


    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return  code ;
    }
}

