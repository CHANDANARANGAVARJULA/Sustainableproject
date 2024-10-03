function registerUser(event) {
    event.preventDefault(); // Prevent the default form submission

    // Gather form data
    const userId = document.getElementById('userId').value; // Get user ID
    const firstName = document.getElementById('firstName').value;
    const lastName = document.getElementById('lastName').value;
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;
    const phoneNumber = document.getElementById('phoneNumber').value;

    // Sending the data to the server (adjust the URL as needed)
    fetch('http://localhost:8080/register', { // Changed to match the correct endpoint
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId, firstName, lastName, email, password, phoneNumber }) // Include user ID in the body
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert("Registration successful! Redirecting to login page...");
            window.location.href = 'login.html'; // Redirect to login page
        } else {
            alert("Registration failed: " + data.message);
        }
    })
    .catch(error => console.error('Error:', error));
}
