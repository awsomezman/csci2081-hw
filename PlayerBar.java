public class PlayerBar extends Obstacle {
    private double barHeight;
    private double barWidth;

    public PlayerBar(double xPos, double yPos, double h, double w) {
        super(xPos, yPos, 0.05);
        this.barHeight = h;
        this.barWidth = w;
    }
    public double getBarHeight() {
        return barHeight;
    }
    public double getBarWidth() {
        return barWidth;
    }
    public void setBarHeight(double barHeight) {
        this.barHeight = barHeight;
    }
    public void setBarWidth(double barWidth) {
        this.barWidth = barWidth;
    }
    public boolean checkCollision(Particle p) {
        double distance = Vector2DMath.magnitude(super.x - p.getX(), y - p.getY());
        return (distance <= radius + p.getRadius());
    }
}
