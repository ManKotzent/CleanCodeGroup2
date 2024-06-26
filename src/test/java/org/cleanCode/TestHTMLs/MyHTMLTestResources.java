package org.cleanCode.TestHTMLs;

public class MyHTMLTestResources {
    public final static String html = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Sample HTML Site</title>
            </head>
            <body>
                <!-- This is a comment -->
                <header>
                    <h1>Welcome to My Website</h1>
                </header>
                <nav>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Services</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </nav>
                <main>
                    <section>
                        <h2>About Us</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
                    </section>
                    <section>
                        <h2>Our Services</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
                    </section>
                    <section>
                        <h2>Contact Us</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
                    </section>
                </main>
                <footer>
                    <p>&copy; 2024 Sample HTML Site</p>
                </footer>
            </body>
            </html>""";

    public final static String htmlNoComments = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Sample HTML Site</title>
            </head>
            <body>
               \s
                <header>
                    <h1>Welcome to My Website</h1>
                </header>
                <nav>
                    <ul>
                        <li><a href="#">Home</a></li>
                        <li><a href="#">About</a></li>
                        <li><a href="#">Services</a></li>
                        <li><a href="#">Contact</a></li>
                    </ul>
                </nav>
                <main>
                    <section>
                        <h2>About Us</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
                    </section>
                    <section>
                        <h2>Our Services</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
                    </section>
                    <section>
                        <h2>Contact Us</h2>
                        <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
                    </section>
                </main>
                <footer>
                    <p>&copy; 2024 Sample HTML Site</p>
                </footer>
            </body>
            </html>""";

    public final static String htmlExtraLinks1 = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Sample HTML Site</title>
            </head>
            <body>
            <!-- This is a comment -->
            <header>
            <h1>Welcome to My Website</h1>
            </header>
            <nav>
            <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
            </ul>
            </nav>
            <main>
            <section>
            <h2>About Us</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
            </section>
            <section>
            <h2>Our Services</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
            </section>
            <section>
            <h2>Contact Us</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
            </section>
            <!-- Additional Links -->
            <section>
            <h2>Additional Links</h2>
            <ul>
            <li><a href="https://www.google.com">Google</a></li>
            <li><a href="https://motherfuckingwebsite.com/">Motherfucking Website</a></li>
            </ul>
            </section>
            </main>
            <footer>
            <p>&copy; 2024 Sample HTML Site</p>
            </footer>
            </body>
            </html>""";

    public final static String htmlExtraLinks2 = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Sample HTML Site</title>
            </head>
            <body>
            <!-- This is a comment -->
            <header>
            <h1>Welcome to My Website</h1>
            </header>
            <nav>
            <ul>
            <li><a href="#">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="#">Services</a></li>
            <li><a href="#">Contact</a></li>
            </ul>
            </nav>
            <main>
            <section>
            <h2>About Us</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
            </section>
            <section>
            <h2>Our Services</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
            </section>
            <section>
            <h2>Contact Us</h2>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed vitae enim magna.</p>
            </section>
            <!-- Additional Links -->
            <section>
            <h2>Additional Links</h2>
            <ul>
            <li><a href="https://motherfuckingwebsite.com/">Motherfucking Website</a></li>
            </ul>
            </section>
            </main>
            <footer>
            <p>&copy; 2024 Sample HTML Site</p>
            </footer>
            </body>
            </html>""";
}
