import java.lang.Math;

public class Particle {
    private double x;
    private double y;
    private double radius;
    private double velocityX;
    private double velocityY;
    private double gravity;
    private int score;
    private int pointMult;
    private int timesBounced;


    public Particle(double xPos, double yPos, double r, double grav) {
        this.x = xPos;
        this.y = yPos;
        this.radius = r;
        this.velocityX = 0.0;
        this.velocityY = -0.5;
        this.gravity = grav;
        this.score = 0;
        this.pointMult = 1;
        this.timesBounced = 0;
    }
    public int getTimesBounced(){
      return timesBounced;
    }
    public double getX() {
        return x;
    }
    
    
    public int getScore() {
      return score;
    }
    
    public double getY() {
        return y;
    }

    public double getRadius() {
        return radius;
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public double getGravity() {
        return gravity;
    }

    public void setX(double xPos) {
        this.x = xPos;
    }

    public void setY(double yPos) {
        this.y = yPos;
    }

    public void setRadius(double r) {
        if (r > 0) {
            this.radius = r;
        }
        else {
            System.out.println("Radius must be positive.");
        }
    }

    public void setVelocityX(double vx) {
        this.velocityX = vx;
    }

    public void setVelocityY(double vy) {
        this.velocityY = vy;
    }

    public void setGravity(double g) {
        this.gravity = g;
    }

    public void update(double dt) {
        //velocity = velocity + acceleration * dt
        velocityY = velocityY + gravity*dt;

        // position = position + velocity * dt
        x = x + velocityX * dt;
        y = y + velocityY * dt;

        //if the particle hits the floor (when y is at radius - means that the particle's edge hits the floor)
       
       if (x <= radius) {
            //correction
            x = radius;
            //bounce
            velocityX = -1.0 * velocityX;
       }    
       if (y >= 1 - radius) {
            y = 1 - radius;

            velocityY = -1.0 * velocityY;
       }
        
       if (x >= 1 - radius) {
            x = 1 - radius;

            velocityX = -1.0 * velocityX;
       }
       if (Math.abs(velocityY) < 0.5) {
         velocityY = 0.5;
       }

    }

    public void handleCollision(Obstacle obstacle) {
        if (obstacle.checkCollision(this)) {
            double[] normal = obstacle.collide(this);

            //bounce (reflect)
            //if velocity is too small to bounce, lets bounce it anyway
            if (Vector2DMath.magnitude(velocityX, velocityY) < 0.2) {
                velocityX = normal[0];
                velocityY = normal[1];
            }
            else {
                double[] reflect = Vector2DMath.reflect(normal, velocityX, velocityY);
                velocityX = reflect[0];
                velocityY = reflect[1];
            }
            
            if (obstacle instanceof PlayerBar) {
                if (velocityY < 0) {
                    velocityY = -velocityY;
                }
                
                score += 1 * pointMult;
                timesBounced += 1;
                if (timesBounced % 10 == 0) {
                  velocityX = velocityX * 1.25;
                  velocityY = velocityY * 1.25;
                  pointMult = pointMult * 2;
                }
            } 
        }
    }

    public void setVelocity(double vx, double vy) {
        velocityX = vx;
        velocityY = vy;
    }
}
