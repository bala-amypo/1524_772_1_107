@Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getServletPath();

    // 🚀 Skip JWT validation for public endpoints
    if (path.startsWith("/auth/")
            || path.equals("/users/register")
            || path.startsWith("/v3/api-docs")
            || path.startsWith("/swagger-ui")
            || (path.startsWith("/parcel") && request.getMethod().equals("GET"))) {

        filterChain.doFilter(request, response);
        return;
    }

    String authHeader = request.getHeader("Authorization");

    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
    }

    // continue JWT validation logic...
}

