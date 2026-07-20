package com.naviroq.patterns.structural.proxy.accessControl.proxyControl;

import com.naviroq.patterns.structural.proxy.accessControl.Document;
import com.naviroq.patterns.structural.proxy.accessControl.RealDocumentView;

public class ProxyViewer implements Document {
    // class instance attributes
    private final RealDocumentView realViewer;
    private final String currentUserRole;

    public ProxyViewer(String fileName, String content, String role) {
        if (role == null || role.isEmpty()){
            throw new RuntimeException("FORBIDDEN ACTION");
        }
        realViewer = new RealDocumentView(fileName, content);
        this.currentUserRole = role;
    }

    @Override
    public void view() {
        realViewer.view();
    }

    @Override
    public void edit(String content) {
        if (!currentUserRole.equals("ADMIN") && !currentUserRole.equals("EDITOR")) {
            System.out.println("[Proxy] ACCESS DENIED: " + currentUserRole + " cannot edit");
            return;
        }
        realViewer.edit(content);
    }

    @Override
    public void delete() {
        if (!currentUserRole.equals("ADMIN")) {
            System.out.println("[Proxy] ACCESS DENIED: " + currentUserRole + " cannot delete");
            return;
        }
        realViewer.delete();
    }
}
