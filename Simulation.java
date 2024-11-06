public class Simulation {
    private int numParticles;
    private ObstacleNode obstacleList;
    private Particle[] particles;
    
    public Simulation() {
        this.numParticles = 0;
        this.obstacleList = new ObstacleNode(null);
        this.particles = new Particle[100];
    }
    
    public Particle[] getParticles() {
      return particles;
    }
    public int getNumParticles() {
      return numParticles;
    }
    public void addParticles(int amount) {
        if (numParticles + amount >= particles.length) {
            Particle[] newArray = new Particle[particles.length*2];
            for (int i = 0; i < particles.length; ++i) {
                newArray[i] = particles[i];
            }
            particles = newArray;
        }
        int index = numParticles;
        for (int i = 0; i < amount; ++i) {
            particles[index + i] = new Particle(((1.0*i)/amount), 1.0, 0.01, 0.0);
            particles[index + i].setVelocity(0.0, 0.0);
            ++numParticles;
        }
    }
    public void removeParticles(int amount) {
      if (numParticles - amount < 0) {
        return;
      }
      for (int i = 0; i < amount; ++i) {
        particles[numParticles - 1] = null;
        --numParticles;
      }
    }
  public ObstacleNode getObstacles() {
    return obstacleList;
  }
  public void addObstacle(Obstacle obstacle) {
    ObstacleNode addedObstacle = new ObstacleNode(obstacle);
    ObstacleNode n = obstacleList;
    while (n.getNext() != null) {
        n = n.getNext();
    }
    n.setNext(addedObstacle);
  }
  public void removeObstacle(Obstacle obstacle) {
    for (ObstacleNode n = obstacleList; n != null; n = n.getNext()) {
      if (n.getNext().getObstacle() == obstacle) {
        n.setNext(n.getNext().getNext());
        break;
      }
    }
  }
}
