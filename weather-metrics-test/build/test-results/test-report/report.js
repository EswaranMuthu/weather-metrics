$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("01-add-measurement.feature");
formatter.feature({
  "line": 2,
  "name": "Cucumber run test",
  "description": "\r\n  I want to run a sample feature file.",
  "id": "cucumber-run-test",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@SmokeTest"
    }
  ]
});
formatter.scenario({
  "line": 6,
  "name": "Add a measurement with valid (numeric) values",
  "description": "",
  "id": "cucumber-run-test;add-a-measurement-with-valid-(numeric)-values",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 7,
      "value": "# POST /measurements"
    }
  ],
  "line": 8,
  "name": "I submit a new measurement as follows:",
  "rows": [
    {
      "cells": [
        "timeStamp",
        "temperature",
        "dewPoint",
        "precipitation"
      ],
      "line": 9
    },
    {
      "cells": [
        "\"2015-09-01T16:00:00.000Z\"",
        "27.1",
        "16.7",
        "0"
      ],
      "line": 10
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 11,
  "name": "the response has a status code of 201",
  "keyword": "Then "
});
formatter.match({
  "location": "AddMeasurementSteps.whenStatement(DataTable)"
});
formatter.result({
  "duration": 1377783038,
  "status": "passed"
});
formatter.match({
  "location": "AddMeasurementSteps.thenStatement()"
});
formatter.result({
  "duration": 2135135,
  "status": "passed"
});
formatter.scenario({
  "line": 13,
  "name": "Cannot add a measurement with invalid values",
  "description": "",
  "id": "cucumber-run-test;cannot-add-a-measurement-with-invalid-values",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 14,
      "value": "# POST /measurements"
    }
  ],
  "line": 15,
  "name": "I submit a new measurement as follows:",
  "rows": [
    {
      "cells": [
        "timestamp",
        "temperature",
        "dewPoint",
        "precipitation"
      ],
      "line": 16
    },
    {
      "cells": [
        "\"2015-09-01T16:00:00.000Z\"",
        "\"not a number\"",
        "16.7",
        "0"
      ],
      "line": 17
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 18,
  "name": "the response has a status code of 400",
  "keyword": "Then "
});
formatter.match({
  "location": "AddMeasurementSteps.whenStatement(DataTable)"
});
formatter.result({
  "duration": 27453197,
  "status": "passed"
});
formatter.match({
  "location": "AddMeasurementSteps.thenNegativeSenario()"
});
formatter.result({
  "duration": 26053,
  "status": "passed"
});
formatter.scenario({
  "line": 20,
  "name": "Cannot add a measurement without a timestamp",
  "description": "",
  "id": "cucumber-run-test;cannot-add-a-measurement-without-a-timestamp",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 21,
      "value": "# POST /measurements"
    }
  ],
  "line": 22,
  "name": "I submit a new measurement as follows:",
  "rows": [
    {
      "cells": [
        "temperature",
        "dewPoint",
        "precipitation"
      ],
      "line": 23
    },
    {
      "cells": [
        "27.1",
        "20",
        "0"
      ],
      "line": 24
    }
  ],
  "keyword": "When "
});
formatter.step({
  "line": 25,
  "name": "the response has a status code of 400",
  "keyword": "Then "
});
formatter.match({
  "location": "AddMeasurementSteps.whenStatement(DataTable)"
});
formatter.result({
  "duration": 11371908,
  "status": "passed"
});
formatter.match({
  "location": "AddMeasurementSteps.thenNegativeSenario()"
});
formatter.result({
  "duration": 21521,
  "status": "passed"
});
formatter.uri("02-get-measurement.feature");
formatter.feature({
  "line": 2,
  "name": "Get a measurement",
  "description": "In order to learn what weather conditions were like at a specific time\r\nI want to be able to retrieve a measurement of several metrics at that time",
  "id": "get-a-measurement",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@SmokeTest"
    }
  ]
});
formatter.background({
  "line": 6,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "comments": [
    {
      "line": 7,
      "value": "# POST /measurements"
    }
  ],
  "line": 8,
  "name": "I have submitted new measurements as follows:",
  "rows": [
    {
      "cells": [
        "timeStamp",
        "temperature",
        "dewPoint",
        "precipitation"
      ],
      "line": 9
    },
    {
      "cells": [
        "\"2015-09-01T16:00:00.000Z\"",
        "27.1",
        "16.7",
        "0"
      ],
      "line": 10
    },
    {
      "cells": [
        "\"2015-09-01T16:10:00.000Z\"",
        "27.3",
        "16.9",
        "0"
      ],
      "line": 11
    },
    {
      "cells": [
        "\"2015-09-01T16:20:00.000Z\"",
        "27.5",
        "17.1",
        "0"
      ],
      "line": 12
    },
    {
      "cells": [
        "\"2015-09-01T16:30:00.000Z\"",
        "27.4",
        "17.3",
        "0"
      ],
      "line": 13
    },
    {
      "cells": [
        "\"2015-09-01T16:40:00.000Z\"",
        "27.2",
        "17.2",
        "0"
      ],
      "line": 14
    },
    {
      "cells": [
        "\"2015-09-02T16:00:00.000Z\"",
        "28.1",
        "18.3",
        "0"
      ],
      "line": 15
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "AddMeasurementSteps.givenStatement(DataTable)"
});
formatter.result({
  "duration": 56107053,
  "status": "passed"
});
formatter.scenario({
  "line": 17,
  "name": "Get a specific measurement",
  "description": "",
  "id": "get-a-measurement;get-a-specific-measurement",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 18,
      "value": "# GET /measurements/2015-09-01T16:20Z"
    }
  ],
  "line": 19,
  "name": "I get a measurement for \"2015-09-01T16:20Z\"",
  "keyword": "When "
});
formatter.step({
  "line": 20,
  "name": "the response has a status code of 200",
  "keyword": "Then "
});
formatter.step({
  "line": 21,
  "name": "the response body is:",
  "rows": [
    {
      "cells": [
        "timestamp",
        "temperature",
        "dewPoint",
        "precipitation"
      ],
      "line": 22
    },
    {
      "cells": [
        "\"2015-09-01T16:20Z\"",
        "27.5",
        "17.1",
        "0"
      ],
      "line": 23
    }
  ],
  "keyword": "And "
});
formatter.match({
  "location": "GetMeasurementSteps.whenStatement()"
});
formatter.result({
  "duration": 8771612,
  "status": "passed"
});
formatter.match({
  "location": "GetMeasurementSteps.getMeasurement()"
});
formatter.result({
  "duration": 40400,
  "status": "passed"
});
formatter.match({
  "location": "GetMeasurementSteps.checkResponseJson(DataTable)"
});
formatter.result({
  "duration": 127995,
  "status": "passed"
});
formatter.background({
  "line": 6,
  "name": "",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "comments": [
    {
      "line": 7,
      "value": "# POST /measurements"
    }
  ],
  "line": 8,
  "name": "I have submitted new measurements as follows:",
  "rows": [
    {
      "cells": [
        "timeStamp",
        "temperature",
        "dewPoint",
        "precipitation"
      ],
      "line": 9
    },
    {
      "cells": [
        "\"2015-09-01T16:00:00.000Z\"",
        "27.1",
        "16.7",
        "0"
      ],
      "line": 10
    },
    {
      "cells": [
        "\"2015-09-01T16:10:00.000Z\"",
        "27.3",
        "16.9",
        "0"
      ],
      "line": 11
    },
    {
      "cells": [
        "\"2015-09-01T16:20:00.000Z\"",
        "27.5",
        "17.1",
        "0"
      ],
      "line": 12
    },
    {
      "cells": [
        "\"2015-09-01T16:30:00.000Z\"",
        "27.4",
        "17.3",
        "0"
      ],
      "line": 13
    },
    {
      "cells": [
        "\"2015-09-01T16:40:00.000Z\"",
        "27.2",
        "17.2",
        "0"
      ],
      "line": 14
    },
    {
      "cells": [
        "\"2015-09-02T16:00:00.000Z\"",
        "28.1",
        "18.3",
        "0"
      ],
      "line": 15
    }
  ],
  "keyword": "Given "
});
formatter.match({
  "location": "AddMeasurementSteps.givenStatement(DataTable)"
});
formatter.result({
  "duration": 43329467,
  "status": "passed"
});
formatter.scenario({
  "line": 25,
  "name": "Get a measurement that does not exist",
  "description": "",
  "id": "get-a-measurement;get-a-measurement-that-does-not-exist",
  "type": "scenario",
  "keyword": "Scenario"
});
formatter.step({
  "comments": [
    {
      "line": 26,
      "value": "# GET /measurements/2015-09-01T16:50:00.000Z"
    }
  ],
  "line": 27,
  "name": "I get a measurement for \"2015-09-01T16:50:00.000Z\"",
  "keyword": "When "
});
formatter.step({
  "line": 28,
  "name": "the response has a status code of 404",
  "keyword": "Then "
});
formatter.match({});
formatter.result({
  "status": "undefined"
});
formatter.match({
  "location": "GetMeasurementSteps.invalidInput()"
});
formatter.result({
  "status": "skipped"
});
});