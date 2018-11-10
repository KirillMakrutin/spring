import React, {Component} from 'react';
import './App.css';
import axios from 'axios';
import EmployeeList from "./EmployeeList/EmployeeList";

class App extends Component {
    state = {
        employees: []
    };

    componentDidMount() {
        axios.get('http://localhost:8080/api/employees')
            .then(response => this.setState({
                employees: response.data._embedded.employees
            }))
    }

    render() {
        return (
            <div className="App">
                <header className="App-header">
                    <h1>Employees</h1>
                </header>
                <EmployeeList employees={this.state.employees}/>
            </div>
        );
    }
}

export default App;
