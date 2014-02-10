package com.sourceclear.headlines.impl;

import com.sourceclear.headlines.AbstractHeaderLinesInjector;
import com.sourceclear.headlines.impl.cdpbeans.AllowAccessFromConfig;
import com.sourceclear.headlines.impl.cdpbeans.AllowHttpRequestHeadersConfig;
import com.sourceclear.headlines.impl.cdpbeans.SiteControlConfig;
import com.sourceclear.headlines.impl.util.HeaderBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class CrossDomainPolicyInjector extends AbstractHeaderLinesInjector<CrossDomainPolicyConfig> {

    public CrossDomainPolicyInjector() {
        setConfig(new CrossDomainPolicyConfig());
    }

    /**
     * Creating header "Cross-Domain-Policy" for server response
     *
     * @param request
     * @param response
     */
    public void inject(HttpServletRequest request, HttpServletResponse response) {
        CrossDomainPolicyConfig config = getConfig();

        if (config.isEnabled()) {
            String value = "";
            SiteControlConfig siteControlConfig = config.getSiteControl();
            if (siteControlConfig != null && config.getSiteControl().isEnabled() && !siteControlConfig.getPermittedPolicies().isEmpty()) {
                value = String.format("%s %s;", SiteControlConfig.HEADER_NAME, HeaderBuilder.formatDirectives(siteControlConfig.getPermittedPolicies(), "=", "")).trim();
            }

            AllowAccessFromConfig accessDomainsConfig = config.getAllowAccessFrom();
            if (accessDomainsConfig != null && !accessDomainsConfig.getDomains().isEmpty()) {
                value = String.format("%s %s %s secure=%s;", value, AllowAccessFromConfig.HEADER_NAME, HeaderBuilder.formatDirectives(accessDomainsConfig.getDomains(), "=", "").trim(),
                        accessDomainsConfig.isSecure());
            }

            AllowHttpRequestHeadersConfig accessHttpConfig = config.getAllowHttpRequestHeadersConfig();
            if (accessHttpConfig != null && !accessHttpConfig.getDomains().isEmpty()) {
                value = String.format("%s %s %s", value, AllowHttpRequestHeadersConfig.HEADER_NAME, HeaderBuilder.formatDirectives(accessHttpConfig.getDomains(), "=", "")).trim();
                if (!accessHttpConfig.getHeaders().isEmpty()) {
                    value = String.format("%s %s", value, HeaderBuilder.formatDirectives(accessHttpConfig.getHeaders(), "=", "")).trim();
                }
                value = String.format("%s secure=%s", value, accessHttpConfig.isSecure());
            }

            if (!value.isEmpty()) {
                response.setHeader("Cross-Domain-Policy", value);
            }
        }
    }
}
