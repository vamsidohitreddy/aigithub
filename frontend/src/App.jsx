import './App.css'
import { getHealth } from './services/healthService';
import { useEffect, useState } from "react";

function App() {
  const [health, setHealth]=useState(null);
  const [loading, setLoading]=useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    const checkHealth = async () => {
      try {
        const data = await getHealth();
        setHealth(data);
      } catch {
        setError("Unable to connect to the backend.");
      } finally {
        setLoading(false);
      }
    };

    checkHealth();
  }, []);

  return (
    <main className="container">
      <h1>AI GitHub Project Analyzer</h1>
      <h2>Connection Status</h2>

      {loading && <p>Checking connection...</p>}

      {error && <p className="error">{error}</p>}

      {health && (
        <section className="status">
          <p>Backend: {health.status}</p>
          <p>Database: {health.database}</p>
          <p>Database name: {health.databaseName}</p>
        </section>
      )}
    </main>
  );
}

export default App;
