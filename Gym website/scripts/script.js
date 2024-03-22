/*
Final Project
Written by: (Olivier Leone 2045231, Michael Sousa 1960056)
*“We both came up with this part”
For “Internet Programming” Section ( Sec No.00001)– Fall 2023
*/

//variables
var video = document.getElementById("example");
var videoSource = document.getElementById("vid-src");
var termsContentVisible = false;

//Function to display the burpees example video
function burpees() {
  videoSource.src = "media/burpees.mp4";

  video.style.display = "block";
  video.load();
}

//Function to display the Push up example video
function push() {
  videoSource.src = "media/push.mp4";

  video.style.display = "block";
  video.load();
}

//Function to display the mountain climbers example video
function pull() {
  videoSource.src = "media/pull.mp4";

  video.style.display = "block";
  video.load();
}

function calculateBMI() {
  var age = parseInt(document.getElementById("age").value);
  var gender = document.getElementById("gender").value;
  var weight = parseFloat(document.getElementById("weight").value);
  var feet = parseInt(document.getElementById("feet").value);
  var inches = parseInt(document.getElementById("inches").value);
  var activity = document.getElementById("activity").value;
  var goal = document.getElementById("goal").value;

  // Check if all fields are filled
  if (isNaN(age) || isNaN(weight) || isNaN(feet) || isNaN(inches)) {
    alert("Please fill in all the fields with valid numbers.");
    return;
  }

  // Convert feet and inches to total inches
  var heightInInches = feet * 12 + inches;

  // BMI Calculation
  var bmi = (weight / (heightInInches * heightInInches)) * 703;

  // Create a JSON object to represent BMI data
  var bmiData = {
    age: age,
    gender: gender,
    weight: weight,
    feet: feet,
    inches: inches,
    activity: activity,
    goal: goal,
    bmi: bmi.toFixed(2), // Rounded BMI value
  };
  // Convert the JSON object to a JSON string for storage or transmission
  var jsonData = JSON.stringify(bmiData);

  // Display the BMI result on the page
  document.getElementById("result").innerText = "Your BMI: " + bmiData.bmi;

  // Display alert based on age range and goal
  if (age < 0) {
    alert("Please enter a valid age greater than or equal to 0.");
  } else if (age <= 10) {
    alertMessage("under 10", goal);
  } else if (age >= 11 && age <= 20) {
    alertMessage(11, 20, goal);
  } else if (age >= 21 && age <= 30) {
    alertMessage(21, 30, goal);
  } else if (age >= 31 && age <= 40) {
    alertMessage(31, 40, goal);
  } else if (age >= 41 && age <= 50) {
    alertMessage(41, 50, goal);
  } else if (age >= 51 && age <= 60) {
    alertMessage(51, 60, goal);
  } else if (age >= 61 && age <= 70) {
    alertMessage(61, 70, goal);
  } else if (age >= 71 && age <= 80) {
    alertMessage(71, 80, goal);
  } else if (age >= 81 && age <= 90) {
    alertMessage(81, 90, goal);
  } else if (age >= 91 && age <= 100) {
    alertMessage(91, 100, goal);
  } else if (age > 122) {

    // Assuming the oldest recorded age is 122 (oldest person alive)
    alert("Please enter a valid age below 123.");
  } else {
    // Default message for other age ranges
    alert(
      "Consider consulting with a healthcare professional for personalized guidance based on your age and goals."
    );
  }
  console.log("BMI data in JSON format:", jsonData);
}

// alert messages based on the age and goal
function alertMessage(minAge, maxAge, goal) {
  if (goal === "lose") {
    alert(
      `You're in the age range ${minAge}-${maxAge}! Consult with a healthcare professional for a safe and effective weight loss plan.`
    );
  } else if (goal === "maintain") {
    alert(
      `Great! You're in the age range ${minAge}-${maxAge}. Focus on maintaining a healthy lifestyle with balanced nutrition and regular physical activity.`
    );
  } else if (goal === "gain") {
    alert(
      `For healthy weight gain, considering your age range ${minAge}-${maxAge}, incorporate nutrient-dense foods into your diet. Consult with a professional for guidance.`
    );
  }
}

// Add this function to your script.js file
function calculateWaistHipRatio() {
  var waist = parseFloat(document.getElementById("waist").value);
  var hip = parseFloat(document.getElementById("hip").value);

  // Check if both fields are filled
  if (isNaN(waist) || isNaN(hip)) {
    alert("Please fill in both waist and hip circumference with valid numbers.");
    return;
  }

  // Calculate Waist-to-Hip Ratio
  var ratio = (waist / hip).toFixed(2);

  // Display the result on the page
  var resultElement = document.getElementById("waistHipResult");
  resultElement.innerHTML = "Your Waist-to-Hip Ratio: " + ratio;

  // Interpretation of the ratio 
  if (ratio < 0.9) {
    resultElement.innerHTML += "<p>Your distribution indicates a lower risk of cardiovascular diseases.</p>";
  } else {
    resultElement.innerHTML += "<p>Your distribution indicates a higher risk of cardiovascular diseases.</p>";
  }
}

// Function to calculate Heart Rate
function calculateHeartRate() {
  //store the heartRateAge id in age variable
  const age = document.getElementById("heartRateAge").value;

  //calculation for maximum heart rate
  const maxHeartRate = 220 - age;

  //display the result 
 document.getElementById("resultValue").innerText = maxHeartRate;

  
}

// Function to load and toggle visibility of Terms and Service content
function loadTermsAndService() {

   // Create a new XMLHttpRequest object
  var xhr = new XMLHttpRequest();

  // Define a callback function to handle the state changes of the XMLHttpRequest
  xhr.onreadystatechange = function () {

    // Check if the request has been completed
    if (xhr.readyState == 4) {

       // Check if the request was successful (status code 200)
      if (xhr.status == 200) {

         // Retrieve the content of the terms from the response
        var termsContent = xhr.responseText;

         // Get the HTML element where the terms content will be displayed
        var termsContentDiv = document.getElementById("termsContent");

        // Check if terms content is currently visible
        if (termsContentVisible) {

           // If terms are visible, hide them by clearing the inner HTML
          termsContentDiv.innerHTML = "";
        } else {

          // If terms are not visible, show them by setting the inner HTML with the loaded content
          termsContentDiv.innerHTML = termsContent;
        }

        // Toggle visibility flag
        termsContentVisible = !termsContentVisible;
      } else {
        alert("Error loading Terms and Service. Status: " + xhr.status);
      }
    }
  };

  // File Path
  var filePath = "terms_and_service.txt.txt";

   // Initialize the request, specifying the HTTP method (GET), file path, and asynchronous flag (true)
  xhr.open("GET", filePath, true);

    // Send the XMLHttpRequest to initiate the request
  xhr.send();
}
