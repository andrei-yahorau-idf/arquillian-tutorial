package lookup;



  public enum ServiceType{

    ONE(MyGreeting1.class),
    TWO(MyGreeting2.class);

    Class<? extends MyGreeting> clazz;

    ServiceType(Class<? extends MyGreeting> clazz){
      this.clazz = clazz;
    }

    public Class<? extends MyGreeting> getClazz() {
      return clazz;
    }
  }
