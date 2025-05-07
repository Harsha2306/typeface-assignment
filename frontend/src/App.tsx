import FileViewer from "./pages/FileViewer";
import NotFound from "./pages/NotFound";
import UserHome from "./pages/UserHome";
import { BrowserRouter, Routes, Route } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<UserHome />} />
        <Route path="/files/:id/view" element={<FileViewer />} />
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
