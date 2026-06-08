Simulation sim;
Particle[] p;
ObstacleNode obs;
ObstacleAPI api = new ObstacleAPI();

//Initialization
void setup() {
  size(500, 500);
  ellipseMode(RADIUS);
  sim = new Simulation();
  p  = sim.getParticles();
  obs = sim.getObstacles();
    
}


void mouseClicked() {
  if (mouseButton == LEFT) {
    sim.addObstacle(new Obstacle((1.0 * mouseX)/width, 1.0 - (1.0 * mouseY)/height, 0.075));
    obs = sim.getObstacles();
    System.out.println("Obstacle added");
  }
  if (mouseButton == RIGHT) {
    for (ObstacleNode o = obs.getNext(); o != null; o = o.getNext()) {
      if (o.getObstacle().checkCollision((1.0 * mouseX)/width, 1.0 - (1.0 * mouseY)/height)) {
        sim.removeObstacle(o.getObstacle());
        obs = sim.getObstacles();
        System.out.println("Obstacle removed");
        break;
      }
    }
  }
}


//main loop (repeated over and over again)
void draw() {
  //red green blue (0-255)
  background(100,100,100);
  //particles
  fill(255, 0, 0);
  for (int i = 0; i < p.length; ++i) {
    if (p[i] != null) {
      p[i].update(0.01);
       circle((float)(p[i].getX()*width), height - (float)(p[i].getY()*height), (float)(p[i].getRadius()*width));
    }
  }
  //Obstacles
  fill(0,0,255);
  for (ObstacleNode o = obs.getNext(); o != null; o = o.getNext()) {
    circle((float)(o.getObstacle().getX()*width), height - (float)(o.getObstacle().getY()*height), (float)(o.getObstacle().getRadius()*width));
    for(int i = 0; i < p.length; ++i) {
      if (p[i] != null) {
        p[i].handleCollision(o.getObstacle());
      }
    }
    
  }
}


void keyPressed() {
  if (key == '+' || key == '=') {
    sim.addParticles(50);
    p = sim.getParticles();
    System.out.println("Particles added");
  }
  if (key == '-' || key == '_') {
    sim.removeParticles(50);
    p = sim.getParticles();
    System.out.println("Particles removed");
  }
}
