package enums;

/**
 *
 * @author Adelcio C.Porto
 */
public enum Direction {
    RIGHT("Direita"),
    DOWN("Para Baixo"),
    LEFT("Esquerda"),
    UP("Para Cima");

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    private final String direction;

    private Direction(String direction) {
        this.direction = direction;
    }
}
