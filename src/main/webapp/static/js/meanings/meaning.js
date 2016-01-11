/**
 * 
 */

function makeForm() {

	mypara = document.getElementById("paraID");

	// this is an element already on the page

	// its purpose is to provide a place to put the form

	// if the form were merely added to the document, it would appear at the end

	// now I create some elements

	myform = document.createElement("form");

	myselect = document.createElement("select");

	// one way to write a function... you have to write it yourself!

	myOnChange = new Function("e",
			"location.href=myselect.options[myselect.selectedIndex].value");

	// first option

	// create an option

	theOption = document.createElement("OPTION");

	// make some text

	theText = document.createTextNode("Select A Page To Go");

	// add the text to the option

	theOption.appendChild(theText);

	// add the option to the select

	myselect.appendChild(theOption);

	// second option

	theOption = document.createElement("OPTION");

	theText = document.createTextNode("JavaScript Tutorial II");

	theOption.appendChild(theText);

	// this option has a value, an URL, so we set the value

	theOption.setAttribute("value", "index.html");

	myselect.appendChild(theOption);

	// third option

	theOption = document.createElement("OPTION");

	theText = document.createTextNode("getElementById");

	theOption.appendChild(theText);

	theOption.setAttribute("value", "getElementByID.htm");

	myselect.appendChild(theOption);

	// fourth option

	// this has some variations to show alternative ways of

	// adding values and text

	theOption = document.createElement("OPTION");

	// new ways to set the values and the text

	theOption.value = "modifyingExistingText.htm";

	// set the text using the innerHTML property instead of adding text node

	theOption.innerHTML = "Modifying Existing Text";

	myselect.appendChild(theOption);

	// end of options

	// now the select has the options added with their text, values and onChange
	// event

	myform.appendChild(myselect);

	mypara.appendChild(myform);

	// the myOnChange(e) function, created earlier, is used to set

	// the "onchange" event (Yes, onchange, not onChange!)

	myselect.onchange = myOnChange;

	// here I put the button below the select (not essential), just to
	// demonstrate

	// the following

	mybreak = document.createElement("p");

	myform.appendChild(mybreak);

	// now I create a button

	mybutton = document.createElement("BUTTON");

	// the function here for the button is different from before, to illustrate
	// another

	// way to create a function

	function myOnClick(e) {

		alert('Hello');

	}

	// once again, "onclick" not "onClick"

	mybutton.onclick = myOnClick;

	// setting the height and width isn't essential for IE, but is essential for
	// Netscape

	mybutton.style.height = 20;

	mybutton.style.width = 75;

	// assign the value of the button

	theText = document.createTextNode("Click Me");

	mybutton.appendChild(theText);

	// the code commented out below works fine for IE, but the above code is
	// needed for

	// some other browsers and IE likes it too!

	// mybutton.value="Click Me";

	// now add the button to the form

	myform.appendChild(mybutton);

	myselect.setAttribute("bgColor", "red");

	myselect.style.color = "green";

	myselect.setAttribute("border", "10px");

	myselect.style.fontWeight = "bold";

	// I need to set the IDs because I want to

	// to reference then so I can delete the form later

	myselect.setAttribute("id", "selectID");

	myform.setAttribute("id", "formID");

	// alert(myselect.id);

	// enable delete button

	btnDelete = document.getElementById("deleteID");

	btnDelete.disabled = false;

	// disable create button

	btnCreate = document.getElementById("createID");

	btnCreate.disabled = true;

}

function deleteForm() {

	// here I use the IDs set in the create to get a handle on the elements

	myPara = document.getElementById("paraID");

	myform = document.getElementById("formID");

	// so I can remove them:-)

	myPara.removeChild(myform);

	// and now I set the fixed buttons on the page

	// disable delete button

	btnDelete = document.getElementById("deleteID");

	btnDelete.disabled = true;

	// enable create button

	btnCreate = document.getElementById("createID");

	btnCreate.disabled = false;

}// end of deleteForm()

function count_rabbits() {

	for (var i = 1; i <= 3; i++) {

		// operator + concatenates strings

		alert("Rabbit " + i + " out of the hat!")

	}

}
function crunchifyAjax() {
    $.ajax({
        url : 'ajaxtest.html',
        success : function(data) {
            $('#result').html(data);
        }
    });
}

function inputFocus(i){
    if(i.value==i.defaultValue){ i.value=""; i.style.color="#000"; }
}
function inputBlur(i){
    if(i.value==""){ i.value=i.defaultValue; i.style.color="#848484"; }
}