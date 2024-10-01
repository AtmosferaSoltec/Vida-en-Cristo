import { Logger } from '@nestjs/common';
import * as fs from 'fs';
import * as path from 'path';

const httpsCertificate = () => {
  const isProduction = process.env.PRODUCTION === 'true';
  const logger = new Logger('httpsCertificate');
  logger.log(`Production: ${isProduction}`);
  if (!isProduction) {
    return null;
  }

  const httpsOptions = {
    key: fs.readFileSync(
      path.join(
        '/etc/letsencrypt/live/sv-yaaugkfbpu.cloud.elastika.pe/privkey.pem',
      ),
    ),
    cert: fs.readFileSync(
      path.join(
        '/etc/letsencrypt/live/sv-yaaugkfbpu.cloud.elastika.pe/fullchain.pem',
      ),
    ),
  };
  return httpsOptions;
};

export { httpsCertificate };
