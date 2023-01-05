window.onload = () => {
	let edit_profile_inputs = document.querySelectorAll(".edit-input");
	let edit_profile_info = document.querySelectorAll(".div-info");
	let btn_edit = document.querySelector("#btn-edit");
	let btn_submit = document.querySelector("#submit-btn");	
	
	
	btn_edit.addEventListener('click', (e) => {
		e.preventDefault();
	    edit_profile_inputs.forEach((input) => {
	      input.classList.toggle("visually-hidden");
	    });
	
	    edit_profile_info.forEach((info) => {
	      info.classList.toggle("visually-hidden");
	    });
	    
	    btn_submit.classList.toggle("visually-hidden");
	})
}