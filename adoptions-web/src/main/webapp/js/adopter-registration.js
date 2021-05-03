$(document).ready(function() {
   // add row
   $("#addPhone").click(function() {
      let phoneRows = $('.row');

      let phone = phoneRows.last().clone(true);
      let buttons = phoneRows.find('button');
      buttons.html('Delete');

      buttons.off('click').click(function() {
         $(this).closest('.row').remove();
      });

      phone.find('button').html('New');
      phone.find('input').val('');
      phoneRows.last().parent().append(phone);
   });
});

$(document).add(
    function checkPassword(form) {
        let password1 = form.password1.value;
        let password2 = form.password2.value;

       // If password not entered
       if (password1 === '')
          alert ("Please enter Password");

       // If confirm password not entered
       else if (password2 === '')
          alert ("Please enter confirm password");

       // If Not same return False.
       else if (password1 !== password2) {
          alert ("\nPassword did not match: Please try again...")
          return false;
       }

       // If same return True.
       else{
          alert("Password Match: Welcome to GeeksforGeeks!")
          return true;
       }
    }
);
// Function to check Whether both passwords
// is same or not.



