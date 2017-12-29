# _Framework prototype for Amazon automation testing_ 

[![codebeat badge](https://codebeat.co/badges/5a472cbd-e98e-4391-ab4d-4d431ca5a53f)](https://codebeat.co/projects/github-com-mkgerasimenko-framework-testng-master)
[![GitHub license](https://img.shields.io/badge/license-Apache%202-blue.svg)](https://goo.gl/9GLmMZ)

This project provides automated test example framework for [**_Amazon_**](https://www.amazon.com/) to find and order the following items:

1. Latest **_Playstation_** with a most profitable price for your husband.
2. **_TV show_** with a highest review rate for your Mom.
3. **_Star Wars Lego_** for your 9 years old kid.
4. **_Montale Wild Pears Parfum_** (50 milliliters) for your girlfriend.
5. **_Red NIKE running shoes_** (42 EU size) for your boyfriend.


## 1. Configuration details:
The project uses **TestNG**  testing framework and **Allure** test report framework to create test execution reports.
**Gradle** configure project build automation.

- To supply test data in a more flexible way use TestNG DataProvider wrapper [Test Data Supplier](https://github.com/sskorol/test-data-supplier).

- For browser management and taking screenshots in project was used [WebDriver Supplier](https://github.com/sskorol/webdriver-supplier).

- All scenarios tested on  Firefox 57.0  and Chrome 62.0 browsers in **Docker** containers via **Selenoid.**

### 1.1. Create config/browsers.json configuration file with content:

```json
{
  "firefox": {
    "default": "57.0",
    "versions": {
      "57.0": {
        "image": "selenoid/vnc:firefox_57.0",
        "port": "4444"
      }
    }
  },
  "chrome": {
    "default": "62.0",
    "versions": {
      "62.0": {
        "image": "selenoid/vnc:chrome_62.0",
        "port": "4444"
      }
    }
  }
}
```

### 1.2. To run tests by multi-container Docker use a Docker Compose YAML file to configure your application's services:
**For Windows** use next docker compose YAML file configuration:

```yaml
version: '3'
services:
  selenoid:
    image: "aerokube/selenoid:1.4.0"
    network_mode: bridge
    ports:
      - "4444:4444"
    volumes:
      - "/C/your directory:/etc/selenoid" # assumed current dir contains browsers.json located on C: drive
      - "/C/your directory for video files:/output" # assumed current dir contains video files located on C: drive
      - "//var/run/docker.sock:/var/run/docker.sock"
    environment:
      - "OVERRIDE_VIDEO_OUTPUT_DIR=/C/your directory for video files/"
    command: ["-limit", "6", "-conf", "/etc/selenoid/browsers.json", "-video-output-dir",
 "/your directory for video files located on C: drive"]
  selenoid-ui:
    image: "aerokube/selenoid-ui"
    network_mode: bridge
    links:
      - selenoid
    ports:
      - "8081:8080"
    command: ["--selenoid-uri", "http://selenoid:4444"]
```

**For Mac and Linux** use next docker compose YAML file configuration:

```yaml
version: '3'
services:
  selenoid:
    image: "aerokube/selenoid:1.4.0"
    network_mode: bridge
    ports:
      - "4444:4444"
    volumes:
    - "$PWD:/etc/selenoid/"
    - "$PWD:/output"
    - "//var/run/docker.sock:/var/run/docker.sock"
    environment:
    - "OVERRIDE_VIDEO_OUTPUT_DIR=$PWD"
    command:  ["-limit", "6", "-conf", "/etc/selenoid/browsers.json", "-video-output-dir", "/output"]
    selenoid-ui:
    image: "aerokube/selenoid-ui"
    network_mode: bridge
    links:
    - selenoid
    ports:
    - "8081:8080"
    command: ["--selenoid-uri", "http://selenoid:4444"]
```

## 2. Run Configuration details:

To create Run/Debug Configuration for Gradle use: 
`clean test` gradle task. 

For Allure report generation use:
`allureReport` gradle task.

For find Allure report on Web use: 
`allureServe` gradle task.
