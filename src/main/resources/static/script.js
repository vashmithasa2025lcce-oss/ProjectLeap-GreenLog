const API = "/api";


/* =====================================================
   MESSAGE
===================================================== */

function showMessage(message, error = false) {

    const box = document.getElementById("messageBox");

    const div = document.createElement("div");

    div.className = error
        ? "message error"
        : "message";

    div.innerText = message;

    box.appendChild(div);

    setTimeout(() => {
        div.remove();
    }, 3000);
}


/* =====================================================
   VOLUNTEERS
===================================================== */

document.getElementById("volunteerForm")
    .addEventListener("submit", async function(event) {

    event.preventDefault();

    const volunteer = {

        name:
            document.getElementById("volunteerName").value,

        email:
            document.getElementById("volunteerEmail").value
    };

    try {

        const response = await fetch(
            `${API}/volunteers`,
            {
                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(volunteer)
            }
        );

        if (!response.ok) {
            throw new Error("Unable to create volunteer");
        }

        await response.json();

        showMessage("Volunteer added successfully!");

        this.reset();

        loadVolunteers();
        loadDashboard();

    } catch (error) {

        showMessage(error.message, true);
    }

});


async function loadVolunteers() {

    try {

        const response =
            await fetch(`${API}/volunteers`);

        if (!response.ok) {
            throw new Error("Unable to load volunteers");
        }

        const volunteers =
            await response.json();

        const table =
            document.getElementById("volunteerTable");

        table.innerHTML = "";

        volunteers.forEach(volunteer => {

            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${volunteer.id}</td>

                <td>${volunteer.name}</td>

                <td>${volunteer.email}</td>

                <td>
                    <button
                        onclick="deleteVolunteer(${volunteer.id})">
                        Delete
                    </button>
                </td>
            `;

            table.appendChild(row);

        });

        document.getElementById("volunteerCount")
            .innerText = volunteers.length;

    } catch (error) {

        showMessage(error.message, true);
    }
}


async function deleteVolunteer(id) {

    if (!confirm("Delete this volunteer?")) {
        return;
    }

    try {

        const response =
            await fetch(
                `${API}/volunteers/${id}`,
                {
                    method: "DELETE"
                }
            );

        if (!response.ok) {
            throw new Error("Unable to delete volunteer");
        }

        showMessage("Volunteer deleted");

        loadVolunteers();
        loadDashboard();

    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   PLANTATION DRIVES
===================================================== */

document.getElementById("driveForm")
    .addEventListener("submit", async function(event) {

    event.preventDefault();

    const drive = {

        name:
            document.getElementById("driveName").value,

        location:
            document.getElementById("driveLocation").value,

        driveDate:
            document.getElementById("driveDate").value
    };

    try {

        const response =
            await fetch(
                `${API}/drives`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(drive)
                }
            );

        if (!response.ok) {
            throw new Error("Unable to create drive");
        }

        await response.json();

        showMessage("Plantation drive created!");

        this.reset();

        loadDrives();
        loadDashboard();

    } catch (error) {

        showMessage(error.message, true);
    }

});


async function loadDrives() {

    try {

        const response =
            await fetch(`${API}/drives`);

        if (!response.ok) {
            throw new Error("Unable to load drives");
        }

        const drives =
            await response.json();

        const table =
            document.getElementById("driveTable");

        table.innerHTML = "";

        drives.forEach(drive => {

            const row =
                document.createElement("tr");

            row.innerHTML = `
                <td>${drive.id}</td>

                <td>${drive.name}</td>

                <td>${drive.location}</td>

                <td>${drive.driveDate || "-"}</td>

                <td>
                    <button
                        onclick="deleteDrive(${drive.id})">
                        Delete
                    </button>
                </td>
            `;

            table.appendChild(row);

        });

        document.getElementById("driveCount")
            .innerText = drives.length;

    } catch (error) {

        showMessage(error.message, true);
    }
}


async function deleteDrive(id) {

    if (!confirm("Delete this plantation drive?")) {
        return;
    }

    try {

        const response =
            await fetch(
                `${API}/drives/${id}`,
                {
                    method: "DELETE"
                }
            );

        if (!response.ok) {
            throw new Error("Unable to delete drive");
        }

        showMessage("Drive deleted");

        loadDrives();
        loadDashboard();

    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   TREES
===================================================== */

document.getElementById("treeForm")
    .addEventListener("submit", async function(event) {

    event.preventDefault();

    const species =
        document.getElementById("treeSpecies").value;

    const location =
        document.getElementById("treeLocation").value;

    const datePlanted =
        document.getElementById("datePlanted").value;

    const nextCheckInDate =
        document.getElementById("nextCheckInDate").value;

    const driveId =
        document.getElementById("treeDriveId").value;

    const volunteerId =
        document.getElementById("treeVolunteerId").value;


    const tree = {

        species: species,

        location: location,

        datePlanted: datePlanted,

        nextCheckInDate: nextCheckInDate
    };


    try {

        const response =
            await fetch(
                `${API}/trees?driveId=${driveId}&volunteerId=${volunteerId}`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(tree)
                }
            );

        const data = await response.json();

        if (!response.ok) {

            throw new Error(
                data.message || "Unable to add tree"
            );
        }

        showMessage("Tree added successfully!");

        this.reset();

        loadTrees();
        loadDashboard();

    } catch (error) {

        showMessage(error.message, true);
    }

});


async function loadTrees() {

    try {

        const response =
            await fetch(`${API}/trees`);

        if (!response.ok) {
            throw new Error("Unable to load trees");
        }

        const trees =
            await response.json();

        const table =
            document.getElementById("treeTable");

        table.innerHTML = "";


        trees.forEach(tree => {

            const row =
                document.createElement("tr");


            row.innerHTML = `

                <td>${tree.id}</td>

                <td>${tree.species}</td>

                <td>${tree.location}</td>

                <td>${tree.datePlanted || "-"}</td>

                <td>${tree.nextCheckInDate || "-"}</td>

                <td>
                    <button
                        onclick="deleteTree(${tree.id})">
                        Delete
                    </button>
                </td>

            `;


            table.appendChild(row);

        });


        document.getElementById("treeCount")
            .innerText = trees.length;


    } catch (error) {

        showMessage(error.message, true);
    }
}


async function deleteTree(id) {

    if (!confirm("Delete this tree?")) {
        return;
    }

    try {

        const response =
            await fetch(
                `${API}/trees/${id}`,
                {
                    method: "DELETE"
                }
            );

        if (!response.ok) {
            throw new Error("Unable to delete tree");
        }

        showMessage("Tree deleted");

        loadTrees();
        loadDashboard();

    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   CHECK-INS
===================================================== */

document.getElementById("checkinForm")
    .addEventListener("submit", async function(event) {

    event.preventDefault();


    const treeId =
        document.getElementById("checkinTreeId").value;

    const status =
        document.getElementById("checkinStatus").value;


    const checkin = {

        status: status
    };


    try {

        const response =
            await fetch(
                `${API}/trees/${treeId}/checkins`,
                {
                    method: "POST",

                    headers: {
                        "Content-Type": "application/json"
                    },

                    body: JSON.stringify(checkin)
                }
            );


        const data =
            await response.json();


        if (!response.ok) {

            throw new Error(
                data.message ||
                "Unable to add check-in"
            );

        }


        showMessage("Check-in added successfully!");

        this.reset();

        loadCheckins();

    } catch (error) {

        showMessage(error.message, true);
    }

});


async function loadCheckins() {

    try {

        const response =
            await fetch(`${API}/trees/checkins`);


        if (!response.ok) {
            throw new Error("Unable to load check-ins");
        }


        const checkins =
            await response.json();


        const table =
            document.getElementById("checkinTable");


        table.innerHTML = "";


        checkins.forEach(checkin => {

            const treeId =
                checkin.tree
                    ? checkin.tree.id
                    : "-";


            const row =
                document.createElement("tr");


            row.innerHTML = `

                <td>${checkin.id}</td>

                <td>${treeId}</td>

                <td>${checkin.status}</td>

                <td>${checkin.checkInDate || "-"}</td>

            `;


            table.appendChild(row);

        });


        document.getElementById("checkinCount")
            .innerText = checkins.length;


    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   SURVIVAL RATE
===================================================== */

async function getSurvivalRate() {

    const driveId =
        document.getElementById("survivalDriveId").value;


    if (!driveId) {

        showMessage(
            "Enter a plantation drive ID",
            true
        );

        return;
    }


    try {

        const response =
            await fetch(
                `${API}/trees/reports/survival-rate/${driveId}`
            );


        if (!response.ok) {
            throw new Error(
                "Unable to calculate survival rate"
            );
        }


        const rate =
            await response.json();


        document.getElementById("survivalResult")
            .innerText =
            Number(rate).toFixed(2) + "%";


    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   DUE FOR CHECK-IN
===================================================== */

async function loadDueTrees() {

    try {

        const response =
            await fetch(
                `${API}/trees/reports/due-for-checkin`
            );


        if (!response.ok) {
            throw new Error(
                "Unable to load due trees"
            );
        }


        const trees =
            await response.json();


        const table =
            document.getElementById("dueTreeTable");


        table.innerHTML = "";


        if (trees.length === 0) {

            table.innerHTML = `
                <tr>
                    <td colspan="4">
                        No trees are currently due for check-in.
                    </td>
                </tr>
            `;

            return;
        }


        trees.forEach(tree => {

            const row =
                document.createElement("tr");


            row.innerHTML = `

                <td>${tree.id}</td>

                <td>${tree.species}</td>

                <td>${tree.location}</td>

                <td>${tree.nextCheckInDate}</td>

            `;


            table.appendChild(row);

        });


    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   LEADERBOARD
===================================================== */

async function loadLeaderboard() {

    try {

        const response =
            await fetch(
                `${API}/volunteers/leaderboard`
            );


        if (!response.ok) {
            throw new Error(
                "Unable to load leaderboard"
            );
        }


        const leaderboard =
            await response.json();


        const list =
            document.getElementById("leaderboard");


        list.innerHTML = "";


        leaderboard.forEach((item, index) => {

            const li =
                document.createElement("li");


            li.innerText =
                `${index + 1}. ${item}`;


            list.appendChild(li);

        });


    } catch (error) {

        showMessage(error.message, true);
    }
}


/* =====================================================
   DASHBOARD
===================================================== */

async function loadDashboard() {

    try {

        const volunteers =
            await fetch(
                `${API}/volunteers`
            ).then(response => response.json());


        const drives =
            await fetch(
                `${API}/drives`
            ).then(response => response.json());


        const trees =
            await fetch(
                `${API}/trees`
            ).then(response => response.json());


        const checkins =
            await fetch(
                `${API}/trees/checkins`
            ).then(response => response.json());


        document.getElementById("volunteerCount")
            .innerText = volunteers.length;


        document.getElementById("driveCount")
            .innerText = drives.length;


        document.getElementById("treeCount")
            .innerText = trees.length;


        document.getElementById("checkinCount")
            .innerText = checkins.length;


    } catch (error) {

        console.log(
            "Dashboard loading error:",
            error
        );

    }
}


/* =====================================================
   INITIAL LOAD
===================================================== */

window.addEventListener("load", function() {

    loadDashboard();

    loadVolunteers();

    loadDrives();

    loadTrees();

    loadCheckins();

});