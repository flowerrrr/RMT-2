import logo from './logo.svg';
import './App.css';
import {useEffect, useState} from "react";
import {ThemeProvider, CssBaseline} from '@mui/material';


const restEndpoint = "https://localhost:8453/das-tool-rest/invitations";

const callRestApi = async () => {
    const username = process.env.REACT_APP_USERNAME;
    const password = process.env.REACT_APP_PASSWORD;

    // Encode the credentials
    const base64Credentials = btoa(username + ":" + password);

    const headers = new Headers({
        "Authorization": "Basic " + base64Credentials
    });

    const response = await fetch(restEndpoint, {headers: headers});

    if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
    }
    const jsonResponse = await response.json();
    console.log(jsonResponse);
    return jsonResponse;
};

function RenderResult() {
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        callRestApi().then(
            result => {
                setData(result);
                setLoading(false)
            });
    }, []);

    if (loading) {
        return <div>Loading...</div>;
    }

    return (
        <div>
            <h1>React App</h1>
            <div>
                {data.map(item => (
                    <div>
                        <div>{item.event.dateTime}</div>
                        <div>{item.event.team.name}</div>
                        <div>{item.event.eventType}</div>
                        <div>{item.event.summary}</div>
                        <div>{item.status}</div>
                    </div>
                ))}
            </div>
        </div>
    );
};

function App() {
    return (
        <ThemeProvider>
            {/* CssBaseline helps with consistent baseline styling */}
            <CssBaseline/>
            <div className="App">
                <RenderResult/>
            </div>
        </ThemeProvider>
    );
}

export default App;
