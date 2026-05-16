async function executeRequest(
    endpoint,
    method = 'GET',
    options = {}
) {

    const start = performance.now();

    try {

        const response =
            await fetch(endpoint, options);

        const end = performance.now();

        const responseTime =
            (end - start).toFixed(2);

        const responseText =
            await response.text();

        const size =
            new Blob([responseText]).size;

        let formattedResponse;

        try {

            formattedResponse =
                JSON.stringify(
                    JSON.parse(responseText),
                    null,
                    2
                );

        } catch {

            formattedResponse = responseText;
        }

        /* RESPONSE BODY */

        document.getElementById('response')
            .innerText = formattedResponse;

        /* STATUS CODE */

        document.getElementById('statusCode')
            .innerText = response.status;

        /* RESPONSE TIME */

        document.getElementById('responseTime')
            .innerText =
            responseTime + ' ms';

        /* RESPONSE SIZE */

        document.getElementById('responseSize')
            .innerText =
            size + ' bytes';

        /* TIMESTAMP */

        document.getElementById('timestamp')
            .innerText =
            new Date().toLocaleTimeString();

        /* CURRENT ENDPOINT */

        document.getElementById('endpointInfo')
            .innerText =
            `${method} ${endpoint}`;

        loadMetrics();

    } catch (error) {

        document.getElementById('response')
            .innerText =
            'Error : ' + error;
    }
}

/* =========================
   HELLO ENDPOINT
========================= */

async function callHello() {

    executeRequest(
        '/hello',
        'GET'
    );
}

/* =========================
   STUDENT ENDPOINT
========================= */

async function createStudent() {

    const jsonText =
        document.getElementById(
            'studentJson'
        ).value;

    executeRequest(
        '/student',
        'POST',
        {

            method: 'POST',

            headers: {
                'Content-Type':
                    'application/json'
            },

            body: jsonText
        }
    );
}

/* =========================
   COURSE ENDPOINT
========================= */

async function createCourse() {

    const jsonText =
        document.getElementById(
            'courseJson'
        ).value;

    executeRequest(
        '/course',
        'POST',
        {

            method: 'POST',

            headers: {
                'Content-Type':
                    'application/json'
            },

            body: jsonText
        }
    );
}

/* =========================
   METRICS
========================= */

async function getMetric(uri) {

    try {

        const response = await fetch(
            `/actuator/metrics/http.server.requests?tag=uri:${uri}`
        );

        const data = await response.json();

        if (
            data.measurements &&
            data.measurements.length > 0
        ) {

            return data.measurements[0].value;
        }

        return 0;

    } catch {

        return 0;
    }
}

/* =========================
   LOAD METRICS
========================= */

async function loadMetrics() {

    const hello =
        await getMetric('/hello');

    const student =
        await getMetric('/student');

    const course =
        await getMetric('/course');

    document.getElementById(
        'helloCount'
    ).innerText = hello;

    document.getElementById(
        'studentCount'
    ).innerText = student;

    document.getElementById(
        'courseCount'
    ).innerText = course;
}

/* =========================
   INITIAL LOAD
========================= */

loadMetrics();