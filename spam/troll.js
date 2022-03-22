window.onload = () => {
    setTimeout(spam, 1000);
}

let spam = async () => {
    let response = await fetch('https://www.mattvandenberg.com');
    console.log(response);
    window.location.reload();
}