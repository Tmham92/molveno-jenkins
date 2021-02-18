
$('#password').password({
    closestSelector: '.form-group',
    shortPass: 'The password is too short',
    badPass: 'Weak; try combining letters & numbers',
    goodPass: 'Medium; try using special characters',
    strongPass: 'Strong password',
    containsField: 'The password contains your username',
    enterPass: 'Type your password',
    showPercent: false,
    showText: true, // shows the text tips
    animate: true, // whether or not to animate the progress bar on input blur/focus
    animateSpeed: 'fast', // the above animation speed
    field: false, // select the match field (selector or jQuery instance) for better password checks
    fieldPartialMatch: true, // whether to check for partials in field
    minimumLength: 4 // minimum password length (below this threshold, the score is 0)
});

let input = $("#show_hide_password input");
let icon = $("#show_hide_password i");

icon.on('click', function (event) {
    event.preventDefault();

    if (input.attr("type") === "text") {
        input.attr('type', 'password');
        icon.addClass("fa-eye-slash");
        icon.removeClass("fa-eye");

    } else if (input.attr("type") === "password") {
        input.attr('type', 'text');
        icon.removeClass("fa-eye-slash");
        icon.addClass("fa-eye");
    }
});
