package be.kdg.freeflow.model;

public enum Color {
    R("RED"),
    Y("YELLOW"),
    G("GREEN"),
    L("LIME"),
    T("DONKERGROEN"),
    B("BLUE"),
    C("CYAN"),
    D("DARKBLUE"),
    O("ORANGE"),
    N("BROWN"),
    W("WHITE"),
    GREY("GREY"),
    P("PINK"),
    U("PURPLE");

    private String s;

    Color(String s) {
        this.s = s;
    }

    public String getC() {
        return name();
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}

