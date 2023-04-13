import { useParams } from "react-router";
import './Admin.css'
import EditStand from "../../components/EditStand/EditStand";
import Backbtn from "../../components/Backbtn/Backbtn";

const AdminStandEdit = () => {
    const {id} = useParams()

    const url = window.location.href.split("?")[1];
    const params = new URLSearchParams(url);

    /*function QRCode(){
        let url = 'https://api.qrserver.com/v1/create-qr-code/?data=http://ider-database.westeurope.cloudapp.azure.com:8080/Prosjekt-Expo-0.0.1-SNAPSHOT/stand?id='
        return<div className="qr">
            <img src={url + stand?.id} alt="QrCode"/>
        </div>
    }*/

    return <>
        <div style={{display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column", position: "relative"}}>
            <Backbtn />
            <h2>{id==="-1" ? "Lag stand" : "Rediger stand"}</h2>
            <EditStand isAdd={id==="-1"} eventId={params.get("eventId")} getStandUrl={`/api/stand?id=${id}`} />
        </div>
    </>
}

export default AdminStandEdit;
