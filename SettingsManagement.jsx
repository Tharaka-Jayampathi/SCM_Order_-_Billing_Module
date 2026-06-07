import { useState, useRef } from 'react';
import './SettingsManagement.css';

export default function SettingsManagement() {
  // --- State Variables ---
  const [fullName, setFullName] = useState("Admin User");
  const [email, setEmail] = useState("admin@omakcomputers.lk");
  const [avatar, setAvatar] = useState(null); // Stores base64 or URL string of image
  const [avatarFile, setAvatarFile] = useState(null); // Stores raw file object for backend
  
  // Store Settings State
  const [storeName, setStoreName] = useState("Omak Computers");
  const [contactEmail, setContactEmail] = useState("support@omakcomputers.lk");
  const [currency, setCurrency] = useState("LKR");
  const [taxRate, setTaxRate] = useState("15");

  // Preferences State
  const [orderNotifications, setOrderNotifications] = useState(true);
  const [lowStockAlerts, setLowStockAlerts] = useState(true);
  const [twoFactor, setTwoFactor] = useState(false);


  // Loading indicator for save state
  const [isSaving, setIsSaving] = useState(false);

  // Reference hook to tap the hidden file input
  const fileInputRef = useRef(null);

  // --- Handlers ---
  
  // 1. Change Avatar logic
  const handleAvatarButtonClick = () => {
    fileInputRef.current.click();
  };

  const handleAvatarChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      setAvatarFile(file); // Saved to push to database if needed
      const reader = new FileReader();
      reader.onload = (event) => {
        setAvatar(event.target.result); // Base64 display preview
      };
      reader.readAsDataURL(file);
    }
  };

  

  // 2. Save Changes global handler
  const handleSaveChanges = async () => {
  setIsSaving(true);

  const formData = new FormData();

  formData.append("fullName", fullName);
  formData.append("email", email);
  formData.append("storeName", storeName);
  formData.append("contactEmail", contactEmail);
  formData.append("currency", currency);
  formData.append("taxRate", taxRate);

  formData.append(
    "orderNotifications",
    orderNotifications
  );

  formData.append(
    "lowStockAlerts",
    lowStockAlerts
  );

  formData.append(
    "twoFactor",
    twoFactor
  );

  if (avatarFile) {
    formData.append(
      "avatar",
      avatarFile
    );
  }

  try {

    const response = await fetch(
      "http://localhost:8080/api/settings/update",
      {
        method: "PUT",
        body: formData
      }
    );

    if (response.ok) {
      alert("Settings updated successfully!");
    } else {
      alert("Failed to save settings.");
    }

  } catch (error) {
    console.error(error);
    alert("Server Error");
  } finally {
    setIsSaving(false);
  }
};

  return (
    <div className="main-content">
      <div className="header">
        <h1>Settings</h1>
        <button 
          className="btn btn-primary" 
          onClick={handleSaveChanges}
          disabled={isSaving}
        >
          {isSaving ? "Saving..." : "Save Changes"}
        </button>
      </div>
      
      <div className="settings-grid">
        <div className="settings-card">
          <h2>User Profile</h2>
          <div className="profile-section">
            <div className="avatar-container">
              {/* Hidden file selector tag */}
              <input 
                type="file" 
                ref={fileInputRef} 
                onChange={handleAvatarChange} 
                accept="image/*" 
                style={{ display: 'none' }} 
              />
              <div 
                className="avatar" 
                style={{ 
                  backgroundImage: avatar ? `url(${avatar})` : 'none',
                  backgroundSize: 'cover',
                  backgroundPosition: 'center'
                }}
              >
                {!avatar && "A"}
              </div>
              <button 
                className="btn btn-secondary text-sm" 
                onClick={handleAvatarButtonClick}
              >
                Change Avatar
              </button>
            </div>
            <div className="form-grid">
              <div className="form-group">
                <label>Full Name</label>
                <input 
                  type="text" 
                  value={fullName} 
                  onChange={(e) => setFullName(e.target.value)} 
                  className="form-input" 
                />
              </div>
              <div className="form-group">
                <label>Role</label>
                <input type="text" defaultValue="Administrator" disabled className="form-input" />
              </div>
              <div className="form-group">
                <label>Email</label>
                <input 
                  type="email" 
                  value={email} 
                  onChange={(e) => setEmail(e.target.value)} 
                  className="form-input" 
                />
              </div>
              
            </div>
          </div>
        </div>

        <div className="settings-card">
          <h2>Store Settings</h2>
          <div className="form-grid">
            <div className="form-group">
              <label>Store Name</label>
              <input 
                type="text" 
                value={storeName} 
                onChange={(e) => setStoreName(e.target.value)} 
                className="form-input" 
              />
            </div>
            <div className="form-group">
              <label>Contact Email</label>
              <input 
                type="email" 
                value={contactEmail} 
                onChange={(e) => setContactEmail(e.target.value)} 
                className="form-input" 
              />
            </div>
            <div className="form-group">
              <label>Currency</label>
              <select 
                className="form-input" 
                value={currency} 
                onChange={(e) => setCurrency(e.target.value)}
              >
                <option value="LKR">LKR - Sri Lankan Rupee</option>
                <option value="USD">USD - US Dollar</option>
              </select>
            </div>
            <div className="form-group">
              <label>Tax Rate (%)</label>
              <input 
                type="number" 
                value={taxRate} 
                onChange={(e) => setTaxRate(e.target.value)} 
                className="form-input" 
              />
            </div>
          </div>
        </div>

        <div className="settings-card">
          <h2>User Preferences</h2>
          <div className="preferences-list">
            <label className="checkbox-label">
              <input 
                type="checkbox" 
                checked={orderNotifications} 
                onChange={(e) => setOrderNotifications(e.target.checked)} 
              />
              Enable Email Notifications for New Orders
            </label>
            <label className="checkbox-label">
              <input 
                type="checkbox" 
                checked={lowStockAlerts} 
                onChange={(e) => setLowStockAlerts(e.target.checked)} 
              />
              Show Low Stock Alerts on Dashboard
            </label>
            <label className="checkbox-label">
              <input 
                type="checkbox" 
                checked={twoFactor} 
                onChange={(e) => setTwoFactor(e.target.checked)} 
              />
              Enable Two-Factor Authentication
            </label>
          </div>
        </div>
      </div>
    </div>
  );
}
