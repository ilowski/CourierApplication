const API_URL = 'http://localhost:8080/api';
const TIPS_API_URL = `${API_URL}/tips`;
const LANGS_API_URL = `${API_URL}/langs`;
    const CODE_TO_EMOJI = {
    'en': 'england.png',
    'pl': 'poland.png',
    'de': 'german.png'
    };


    document.getElementById('addTip').addEventListener('click', (event) => {
      event.preventDefault();
      fetch(TIPS_API_URL, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({tipMessage: tipMessageText.value, value: tipText.value})
      })
      .then(updateTip());
        
    });


  
    function updateTip() {
    fetch(TIPS_API_URL, {
     method: 'GET'})
    .then(response => response.text())
    .then((text) => {
      document.getElementById("allTips").innerHTML = ` 
      <h2>${text}</h2>
      `;
    })}



    fetch(LANGS_API_URL)
    .then(response => response.json())
    .then((languages) => {
    const checkboxes = languages.map(l => `
 
    <label class="pure-radio" style="margin: 10px;">
      <input type="radio" name="lang" value="${l.id}">
      <img src="../images/${CODE_TO_EMOJI[l.codeLanguage]}"</img>
    </label>

    `).join('');
    
    document.getElementById('langs').innerHTML = checkboxes;
    });
    const divWelcome = document.getElementById('welcome');
    const form = document.getElementById('welcomeForm');

    document.getElementById('btn').addEventListener('click', (event) => {
    event.preventDefault();
    const formObj = {
    name: form.elements.name.value,
    lang: form.elements.lang.value
    };
    fetch (`${API_URL}?${new URLSearchParams(formObj)}`)
      .then(response => response.text())
      .then((text) => {
       divWelcome.innerHTML = `
       <div class="row">
       <h1>${text}</h1>
       </div>
       `;
       form.remove();
       document.getElementById('tipForm').style.display = 'block';
       });
     
});