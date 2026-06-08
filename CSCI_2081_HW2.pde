Simulation sim;
Particle p;
ObstacleNode obs;
PlayerBar bar;

//Initialization
void setup() {
  size(500, 500);
  ellipseMode(RADIUS);
  sim = new Simulation();
  p  = new Particle(0.5, 0.5, 0.01, 0.0);
  obs = sim.getObstacles();
  bar = new PlayerBar((double) width/2, height - (double) height/10, (double) height/100, (double) width/50);
}



//main loop (repeated over and over again)
void draw() {
  //red green blue (0-255)
  background(100,100,100);
  //particles
  fill(255, 0, 0);

  p.update(0.01);
  circle((float)(p.getX()*width), height - (float)(p.getY()*height), (float)(p.getRadius()*width));

  fill (0, 0, 255);
  circle(mouseX, 450, (float)(bar.getRadius()*width));
  p.handleCollision(bar);


  //Obstacles
  fill(0,0,255);
  for (ObstacleNode o = obs.getNext(); o != null; o = o.getNext()) {
    circle((float)(o.getObstacle().getX()*width), height - (float)(o.getObstacle().getY()*height), (float)(o.getObstacle().getRadius()*width));
    p.handleCollision(o.getObstacle());
  }
}
