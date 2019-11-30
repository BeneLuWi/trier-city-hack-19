import React, {useEffect} from 'react';
import axios from "axios";
import User from "./components/user/User";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Sharer from "./components/sharer/Sharer";

const App = () => {

    return(
        <Router>
            <div>
                <div style={{height:"90vh"}}>
                    <Route path={"/sharer"} render={() => <Sharer/>}/>
                    <Route exact path={"/"} render={() => <User/>}/>
                </div>
                <div className={"w3-bar w3-blue"}>
                    <Link to={"/"} className={"w3-button w3-bar-item w3-left"}>Mitfahren</Link>
                    <Link to={"/sharer"} className={"w3-button w3-bar-item w3-right"}>Fahrt anbieten</Link>
                </div>
            </div>
        </Router>
    )

};

export default App;
