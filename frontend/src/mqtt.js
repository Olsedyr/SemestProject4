import mqtt from 'mqtt';

const broker = 'mqtt://localhost:1883';
const client = mqtt.connect(broker);

function mqttConnect() {
    client.on('connect', () => {
        console.log('Connected to MQTT broker');
        // Perform MQTT operations here, if needed
    });

    client.on('error', (error) => {
        console.error('Error connecting to MQTT broker:', error);
    });

    client.on('close', () => {
        console.log('Disconnected from MQTT broker');
    });
}

export default mqttConnect;