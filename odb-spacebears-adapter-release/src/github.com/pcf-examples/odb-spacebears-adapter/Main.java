
public class Main {
  public static void main(String []args) {
    if (args == null || args.length < 1) {
      System.err.printf("Wrong arguments!");
      System.exit(1);
    }
    switch (args[0]) {
      case "generate-manifest": new ManifestGenerator(args).run();
      case "dashboard-url": new DashboardURL(args).run();
      case "create-binding": new Binder(args).create();
      case "delete-binding": new Binder(args).delete();
      case "generate-plan-schemas": new SchemasGenerator(args).run();
    }
  }
}

class ManifestGenerator {
  String []args;

  ManifestGenerator(String []args) {
    this.args = args;
  }

  static void run() {
    System.out.println("manifest generator run");
  }
}

class DashboardURL {
  String []args;

  DashboardURL(String []args) {
    this.args = args;
  }

  static void run() {
    System.out.println("dashboard-url run");
    System.exit(10); // not implemented
  }
}

class Binder {
  String []args;

  Binder(String []args) {
    this.args = args;
  }

  static void create() {
    System.out.println("create-binding run");
    System.exit(1); // Error!
  }

  static void delete() {
    System.out.println("delete-binding run");
    System.exit(1); // Error!
  }
}

class SchemasGenerator {
  String []args;

  SchemasGenerator(String []args) {
    this.args = args;
  }

  static void run() {
    System.err.println("plan schemas generator run");
    System.exit(10); // not implemented
  }
}