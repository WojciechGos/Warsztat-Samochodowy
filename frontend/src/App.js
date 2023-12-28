
import './App.css';

import { Route, Routes, Navigate } from "react-router-dom"

import PATH from './paths';
import Login from './pages/Authentication/Login';
import Client from './pages/Client';
import Warehouse from './pages/Warehouse';
import Commission from './pages/Commission';
import Vehicle from './pages/Vehicle';
import LocalOrder from './pages/LocalOrder';
import ExternalOrder from './pages/ExternalOrder';
import Wholeseler from './pages/Wholeseler';

import 'bootstrap/dist/css/bootstrap.css';
function App() {
  return (
    <Routes>
      <Route index path={PATH.LOGIN} element={<Login />} />
      <Route path={PATH.CLIENT} element={<Client />} />
      <Route path={PATH.WAREHOUSE} element={<Warehouse/>} />
      <Route path={PATH.COMMISSION} element={<Commission/>}/>
      <Route path={PATH.VEHICLE} element={<Vehicle/>}/>
      <Route path={PATH.LOCALORDER} element={<LocalOrder/>}/>
      <Route path={PATH.EXTERNALORDER} element={<ExternalOrder/>}/>
      <Route path={PATH.WHOLESELER} element={<Wholeseler/>}/>

      <Route path="*" element={<Navigate to={PATH.LOGIN} replace />} />
    </Routes>
  )
}

export default App;
