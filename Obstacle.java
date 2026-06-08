/**
 * <h1> Obstacles! </h1>
 * The Obstacle program implements a class
 * that creates circular obstacles for physics-based 
 * simulations and handles their collision.
 * @author  Matthew Ziegler
 * @version 1.1
 * @since 11/11/2024
 */


public class Obstacle {
    protected double x;
    protected double y;
    protected double radius;

    /**
     * This method initializes the Obstacle object.
     * @param xPos This is used to initialize the x variable
     * @param yPos This is used to initialize the y variable
     * @param r This is used to intialize the radius variable
     */
    public Obstacle(double xPos, double yPos, double r) {
        this.x = xPos;
        this.y = yPos;
        this.radius = r;
    }
    /**
     * Returns the x position of the obstacle
     * @return x variable
     */
    public double getX() {
        return x;
    }
    /**
     * Returns the y position of the obstacle
     * @return y variable
     */
    public double getY() {
        return y;
    }
    /**
     * returns the radius of the obstacle
     * @return radius variable
     */
    public double getRadius() {
        return radius;
    }
    
    /**
     * Sets the x variable
     * @param xPos The value that the x variable is set to
     */
    public void setX(double xPos) {
        this.x = xPos;
    }

    /**
     * Sets the y variable
     * @param yPos The value that the y variable is set to
     */
    public void setY(double yPos) {
        this.y = yPos;
    }

    /**
     * Sets the radius of the obstacle
     * @param r The value that radius is set to
     */
    public void setRadius(double r) {
        if (r > 0) {
            this.radius = r;
        }
        else {
            System.out.println("Radius must be positive.");
        }
    }

    /**
     * Checking whether the obstacle is colliding with
     * a given particle
     * @param p The particle with which the collision is being checked
     * @return A boolean stating whether the obstacle and the particle are colliding
     */
    public boolean checkCollision(Particle p) {
        double distance = Vector2DMath.magnitude(x - p.getX(), y - p.getY());
        return (distance <= radius + p.getRadius());
    }
    
    /**
     * Checking whether the obstacle is
     * colliding with a certain point
     * @param xPos The x-position being checked
     * @param yPos The y-position being checked
     * @return A boolean stating whether the obstacle and the point are colliding
     */
    public boolean checkCollision(float xPos, float yPos) {
      double distance = Vector2DMath.magnitude(x - xPos, y - yPos);
        return (distance <= radius);
    }
    /**
     * This method handles the collision between the obstacle and a particle
     * @param p the particle the obstacle is colliding with
     * @return the normal vector from the collision
     */
    public double[] collide(Particle p) {
        double[] normal = Vector2DMath.normal(p.getX()- this.x, p.getY() - this.y);
        double distance = radius + p.getRadius();
        p.setX(x + normal[0] * distance);
        p.setY(y + normal[1] * distance);
        return normal;
    }
}
