import { Navigate } from "react-router-dom";
import { checkAuth } from "../utils/checkAuth";

const PrivateRoute = ({ children }) => {
    const role = checkAuth();
    if (role === 0) {
        return <Navigate to="/login" />;
    }
    if (role === 2) {
        alert("You are banned from accessing this page.");
        return <Navigate to="/login" />;
    }
    if (role === 102) {
        alert("Admin access granted.");
        return children; // Admin can access the route
    }
    alert("User access granted.");
    return <Navigate to="/chat" />;
}

export default PrivateRoute;