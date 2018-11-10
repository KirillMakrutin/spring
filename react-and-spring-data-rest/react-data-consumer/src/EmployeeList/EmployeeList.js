import React from 'react';
import Employee from "./Employee/Employee";

function EmployeeList(props) {

    const employees = props.employees.map(employee =>
        <Employee key={employee._links.self.href} employee={employee}/>
    );

    return (
        <table>
            <tbody>
            <tr>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Description</th>
            </tr>
            {employees}
            </tbody>
        </table>
    )
}

export default EmployeeList;