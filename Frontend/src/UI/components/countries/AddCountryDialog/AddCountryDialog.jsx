import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle,
    TextField
} from "@mui/material";
import useCountries from "../../../../hooks/useCountries.js";

const initialFormData = {
    "name": "",
    "continent": "",
};

const AddCountryDialog = ({open, onClose, onAdd}) => {
    const [formData, setFormData] = useState(initialFormData);

    const handleChange = (event) => {

        const {name, value} = event.target;
        // const name = event.target.name;
        // const value = event.target.value; --> mozhe i vaka
        //event.target is the element that triggered the event, in this case the <input> field

        setFormData({...formData, [name]: value});

        //...formData --> Copy all the current values from the form
    };

    const handleSubmit = () => {
        onAdd(formData);
        setFormData(initialFormData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Add Country</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Continent"
                    name="continent"
                    value={formData.continent}
                    onChange={handleChange}
                    fullWidth
                />

            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="default">Add</Button>
            </DialogActions>
        </Dialog>
    );
};

export default AddCountryDialog;