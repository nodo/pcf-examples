import org.json.JSONObject;

public class Main {

  public static void main(String []args) throws Exception {
    if (args == null || args.length < 1) {
      error();
    }
    switch (args[0]) {
      case "generate-manifest": new ManifestGenerator(args).run(); break;
      case "dashboard-url": new DashboardURL(args).run(); break;
      case "create-binding": new Binder(args).create(); break;
      case "delete-binding": new Binder(args).delete(); break;
      case "generate-plan-schemas": new SchemasGenerator(args).run(); break;
      default: error();
    }
  }

  private static void error() {
      System.err.printf("Wrong arguments!");
      System.exit(1);
  }
}

class ManifestGenerator {
  String []args;

  ManifestGenerator(String []args) {
    this.args = args;
  }

  void run() throws Exception {
    System.err.println("manifest generator run");

    JSONObject obj = new JSONObject(args[1]);
    String name = obj.getString("deployment_name");

    String[] manifestLines = {
      "name: " + name,
      "releases:",
      "- name: redis-service-dev2",
      "  version: '1+dev.7'",
      "stemcells:",
      "- alias: only-stemcell",
      "  os: ubuntu-trusty",
      "  version: '3468.21'",
      "instance_groups:",
      "- name: redis-server",
      "  instances: 1",
      "  jobs:",
      "  - name: redis-server",
      "    release: redis-service-dev2",
      "  vm_type: t2.small",
      "  stemcell: only-stemcell",
      "  persistent_disk_type: 10GB",
      "  azs:",
      "  - z1",
      "  networks:",
      "  - name: default",
      "  properties:",
      "    redis:",
      "      password: supersecret",
      "update:",
      "  canaries: 4",
      "  canary_watch_time: 30000-240000",
      "  update_watch_time: 30000-240000",
      "  max_in_flight: 4",
      "tags:",
      "  product: redis"
    };
    System.out.println(String.join("\n", manifestLines));
  }
}

class DashboardURL {
  String []args;

  DashboardURL(String []args) {
    this.args = args;
  }

  void run() {
    System.exit(10); // not implemented
  }
}

class Binder {
  String []args;

  Binder(String []args) {
    this.args = args;
  }

  void create() {
    System.exit(1); // Error!
  }

  void delete() {
    System.exit(1); // Error!
  }
}

class SchemasGenerator {
  String []args;

  SchemasGenerator(String []args) {
    this.args = args;
  }

  void run() {
    System.exit(10); // not implemented
  }
}
